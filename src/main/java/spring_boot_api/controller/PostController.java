package spring_boot_api.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring_boot_api.model.Post;
import spring_boot_api.repository.PostRepository;

@RestController
@RequestMapping("/api/v1")
public class PostController {

	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("/posts")
	public List<Post> getAllUsers(){
		return postRepository.findAll();
	}
	
	@GetMapping("/posts/{id}")
	public ResponseEntity<Post> getPostsById(@PathVariable(value="id") Long id) throws ResourceNotFoundException{
		Post post = postRepository
					.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Post not found on: "+id));
		return ResponseEntity.ok().body(post);
	}
	
	@PostMapping("/posts")
	public Post createPost(@Valid @RequestBody Post post) {
		return postRepository.save(post);
	}
	
	@PutMapping("/posts/{id}")
	public ResponseEntity<Post> updatePost(@PathVariable(value="id") Long id, @Valid @RequestBody Post postDetails) throws ResourceNotFoundException{
		Post post = postRepository
				.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Post not found on: "+id));
		
		post.setTitle(postDetails.getTitle());
		post.setBody(postDetails.getBody());
		post.setUpdatedAt(new Date());
		final Post updatedPost = postRepository.save(post);
		return ResponseEntity.ok(updatedPost);
	}
	
	@DeleteMapping("/posts/{id}")
	public Map<String, Boolean> deletePost(@PathVariable(value="id") Long id) throws Exception{
		Post post = postRepository
				.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Post not found on:"+id));
		
		postRepository.delete(post);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted",Boolean.TRUE);
		return response;
	}
}
