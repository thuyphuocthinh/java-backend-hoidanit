package vn.hoidanit.laptopshop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Order;
import vn.hoidanit.laptopshop.domain.OrderDetail;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.CartDetailRepository;
import vn.hoidanit.laptopshop.repository.CartRepository;
import vn.hoidanit.laptopshop.repository.OrderDetailRepository;
import vn.hoidanit.laptopshop.repository.OrderRepository;
import vn.hoidanit.laptopshop.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    public ProductService(ProductRepository productRepository, CartRepository cartRepository,
            CartDetailRepository cartDetailRepository, UserService userService, OrderRepository orderRepository,
            OrderDetailRepository orderDetailRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public void handleSaveProduct(Product product) {
        this.productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public void updateProduct(Product product) {
        this.productRepository.save(product);
    }

    public void deleteProductById(long id) {
        this.productRepository.deleteById(id);
    }

    public Product getProductById(long id) {
        return this.productRepository.findById(id);
    }

    public void handleAddToCart(String email, long productId, HttpSession session) {
        // check user has cart => if not => create a new cart
        User user = this.userService.getUserByEmail(email);
        if (user != null) {
            Cart cart = this.cartRepository.findByUser(user);
            if (cart == null) {
                // create new cart
                Cart otherCart = new Cart();
                otherCart.setUser(user);
                otherCart.setSum(0);
                cart = this.cartRepository.save(otherCart);
            }
            // save product to cart_detail
            Product product = this.getProductById(productId);
            boolean isProductExist = this.cartDetailRepository.existsByCartAndProduct(cart, product);
            if (isProductExist) {
                CartDetail existCd = this.cartDetailRepository.findByCartAndProduct(cart, product);
                existCd.setQuantity(existCd.getQuantity() + 1);
                this.cartDetailRepository.save(existCd);
            } else {
                CartDetail cd = new CartDetail();
                cd.setPrice(product.getPrice());
                cd.setQuantity(1);
                cd.setProduct(product);
                cd.setCart(cart);
                this.cartDetailRepository.save(cd);
            }
            cart.setSum(cart.getSum() + 1);
            session.setAttribute("sum", cart.getSum());
        }
    }

    public Cart fetchByUser(User user) {
        return this.cartRepository.findByUser(user);
    }

    public void handleDeleteCartProduct(long id, HttpSession session) {
        Optional<CartDetail> cartDetailIOptional = this.cartDetailRepository.findById(id);
        if (cartDetailIOptional.isPresent()) {
            CartDetail cartDetail = cartDetailIOptional.get();
            System.out.println(cartDetail);
            Cart cart = cartDetail.getCart();
            cart.setSum((cart.getSum() - cartDetail.getQuantity()));
            session.setAttribute("sum", cart.getSum());
            this.cartDetailRepository.deleteById(cartDetail.getId());
        }
    }

    public void handleUpdateCartBeforeCheckout(List<CartDetail> cartDetails) {
        for (CartDetail cartDetail : cartDetails) {
            Optional<CartDetail> cdOptional = this.cartDetailRepository.findById(cartDetail.getId());
            if (cdOptional.isPresent()) {
                CartDetail currentCartDetail = cdOptional.get();
                currentCartDetail.setQuantity(cartDetail.getQuantity());
                this.cartDetailRepository.save(currentCartDetail);
            }
        }
    }

    public void handlePlaceOrder(User user, HttpSession session, String rN, String rA, String rP) {
        // create order detail
        Cart cart = this.cartRepository.findByUser(user);
        if (cart != null) {
            // create order detail
            List<CartDetail> cartDetails = cart.getCartDetails();
            if (cartDetails != null) {

                // create order
                Order order = new Order();
                order.setUser(user);
                order.setReceiverAddress(rA);
                order.setReceiverName(rN);
                order.setReceiverPhone(rP);
                this.orderRepository.save(order);

                double sum = 0;
                for (CartDetail cartDetail : cartDetails) {
                    sum += cartDetail.getPrice();
                }
                order.setTotalPrice(sum);
                order.setStatus("PENDING");
                order = this.orderRepository.save(order);

                for (CartDetail cartDetail : cartDetails) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrder(order);
                    orderDetail.setPrice(cartDetail.getPrice());
                    orderDetail.setQuantity(cartDetail.getQuantity());
                    orderDetail.setProduct(cartDetail.getProduct());
                    this.orderDetailRepository.save(orderDetail);
                }

                // delete cart detail
                for (CartDetail cartDetail : cartDetails) {
                    this.cartDetailRepository.deleteById(cartDetail.getId());
                }

                // update cart sum
                cart.setSum(0);
                cart.setCartDetails(new ArrayList<CartDetail>());
                this.cartRepository.save(cart);

                // update cart sum session
                session.setAttribute("sum", cart.getSum());
            }
        }
    }
}
