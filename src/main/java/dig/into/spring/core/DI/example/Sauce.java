package dig.into.spring.core.DI.example;

import java.util.List;

public interface Sauce {

	String getSauceType();
	List<Sauce> setSauceList(Sauce sauce);
}
