package spring_boot_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring_boot_api.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{}
