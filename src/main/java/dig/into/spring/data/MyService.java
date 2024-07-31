package dig.into.spring.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class MyService {
	
    @Autowired
    private MyRepository myRepository;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public void addEntity(MyEntity entity) {
        // 엔티티 매니저를 통해 데이터베이스에 저장합니다.
        myRepository.save(entity);
    }
    
    @Transactional
    public MyEntity findAndUpdate(Long id) {
        // 엔티티 매니저를 통해 엔티티를 조회하고 영속성 컨텍스트에 포함시킵니다.
        MyEntity entity = myRepository.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));

        // 엔티티의 상태를 변경합니다.
        entity.setKorName("Updated korName");
        
        // 엔티티의 children 리스트에 접근 (지연 로딩이 발동)
        // children 리스트가 실제로 필요할 때 데이터베이스에서 로딩됩니다.
        for (ChildEntity child : entity.getChildren()) {}

        // 트랜잭션이 완료되면, 변경된 상태가 엔티티 매니저를 통해 데이터베이스에 저장합니다.
        // 쓰기지연
        return myRepository.save(entity);
    }
    
    @Transactional
    public void updateEntity(Long id, String newKorName) {

        MyEntity entity = myRepository.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));

        entity.setKorName(newKorName);

        // 엔티티 매니저가 변경된 엔티티를 자동으로 추적하고, 트랜잭션이 커밋될 때 데이터베이스에 반영합니다.    
    }
    
    @Transactional
    public void example() {
        MyEntity entity1 = entityManager.find(MyEntity.class, 1L); // DB 조회 및 캐시에 저장(1차 캐시)
        MyEntity entity2 = entityManager.find(MyEntity.class, 1L); // 캐시에서 반환
    }
}