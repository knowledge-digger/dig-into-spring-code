package dig.into.spring.core.example;

import java.util.List;

public interface Sauce {

	String getSauceType();
	List<Sauce> setSauceList(Sauce sauce);
}
