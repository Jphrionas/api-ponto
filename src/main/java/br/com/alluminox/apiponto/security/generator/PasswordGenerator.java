package br.com.alluminox.apiponto.security.generator;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class PasswordGenerator implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/*@Autowired
	@Qualifier("bcrypt")
	private PasswordEncoder passwordEncoder;
	*/
	
	/** Generate hash from PasswordEncoder Object 
	 * 
	 * @param word 
	 * @return String
	
	public String generate(String word) {
		if(word == null)  
			return null;
		
		return passwordEncoder.encode(word);
	}
	 */

	/** Match the hash algorith from encoded word  
	 * 
	 * @param word
	 * @param encodedWord 
	 * @return boolean
	 

	public boolean isValid(String word, String encodedWord) {
		if(word == null || encodedWord == null) return false;
		
		return passwordEncoder.matches(word, encodedWord);		
	}
	*/
}
