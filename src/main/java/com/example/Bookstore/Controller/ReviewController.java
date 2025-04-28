package com.example.Bookstore.Controller;

import com.example.Bookstore.RequestBody.AddReviewBody;
import com.example.Bookstore.RequestBody.ViewReviewResponseBody;
import com.example.Bookstore.Service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@Tag(name="Review")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("book/{id}")
    @Operation(summary = "View the list of the reviews from a specific book.")
    public ResponseEntity<List<ViewReviewResponseBody>> getReviewsByBookId(@PathVariable Integer id) {
        try {
            List<ViewReviewResponseBody> reviewDTOs = reviewService.getReviewsByBookId(id);
            return ResponseEntity.ok(reviewDTOs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("book/{id}")
    @Operation(summary = "Add a review")
    public ResponseEntity<String> addReview(@PathVariable Integer id, @RequestBody AddReviewBody reviewBody) {
        try {
            String addedReview = reviewService.addReview(id,reviewBody);
            return ResponseEntity.status(HttpStatus.CREATED).body(addedReview);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
