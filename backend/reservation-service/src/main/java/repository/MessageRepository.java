package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.reservationservice.model.Message;


public interface MessageRepository extends JpaRepository<Message, Long> 
{

}