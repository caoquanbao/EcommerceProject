@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // POST /orders → buyer tạo đơn hàng
    @PostMapping
    @PreAuthorize("hasRole('BUYER')")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order saved = orderService.createOrder(order);
        return ResponseEntity.ok(saved);
    }

    // GET /orders → xem đơn hàng
    @GetMapping
    public ResponseEntity<List<Order>> getOrders(Authentication auth) {
        List<Order> orders = orderService.getOrdersByUser(auth);
        return ResponseEntity.ok(orders);
    }

    // PUT /orders/{id} → seller cập nhật trạng thái
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id,
                                                   @RequestParam Status status) {
        Order updated = orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok(updated);
    }
}
