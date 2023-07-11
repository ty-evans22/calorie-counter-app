package coms309.backend.roundtrip.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import coms309.backend.roundtrip.models.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long>{

}

