package neu.edu.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import neu.edu.entity.Payinfo;

public interface PayinfoDao extends JpaRepository<Payinfo, Integer>{	
	//List<User> findByPersonPersonId(Integer personPersonId);
	public Payinfo findByPayinfoId(Integer PayinfoId);

}
