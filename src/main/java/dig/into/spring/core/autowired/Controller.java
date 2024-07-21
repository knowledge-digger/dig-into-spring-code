package dig.into.spring.core.autowired;

import org.springframework.beans.factory.annotation.Autowired;

public class Controller {

	// 1. 의존성 자동주입 방법 : 필드주입 (권장하지 않음)
	@Autowired
	private Service service;
	
	// 2. 의존성 자동주입 방법 : setter 주입
	@Autowired
	public void setService(Service service) {
		this.service = service;
	}
	
	// 3. 의존성 자동주입 방법 : 생성자 주입 (권장)
	@Autowired
	public Controller(Service service) {
		this.service = service;
	}
	
}
