package ar.com.codoacodo.spring.domain;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
//lombok
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	private Long id;
	private String username;
	private String password;
}
