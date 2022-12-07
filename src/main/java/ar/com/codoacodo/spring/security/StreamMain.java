package ar.com.codoacodo.spring.security;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ar.com.codoacodo.spring.domain.Cupones;

public class StreamMain {

	public static void main(String[] args) {
		List<String> list = List.of("String0","String1","String2");
		
		System.out.println(list);
		
		Set<String> ss = list.stream()
			.map(l -> "bla" + Math.random())
			.collect(Collectors.toSet());
		
		System.out.println(ss);
		
		Cupones cs = Cupones.builder()
			.codigo(null)
			.build();
	}

}
