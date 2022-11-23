package com.spring.jwt_jpa.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.jwt_jpa.entity.MayBay;

@Repository
public interface MayBayRepository extends JpaRepository<MayBay, Integer> {
	@Query(value = "SELECT * FROM maybay  WHERE mamb = ?1", nativeQuery = true)
	List<MayBay> findByMaMB(String mamb);
	
	@Query(value = "SELECT * FROM maybay  WHERE TamBay > ?1", nativeQuery = true)
	List<MayBay> findByTambay(int TamBay);

	@Query(value = "SELECT count(*) FROM maybay  WHERE Loai > ?1", nativeQuery = true)
	int countLoaiMB(String loai);

	@Query("SELECT mb.maMB from MayBay mb, ChungNhan cn, NhanVien nv where mb.maMB = cn.MaMB and cn.MaNV = nv.maNV and nv.ten like ?1")
	List<String> findByHo(String ho);

	@Query("select m.loai from MayBay m where m.tamBay >= (select c.doDai from ChuyenBay c where c.maCB = ?1)")
	List<String> findLoaiMayBay(String chuyenbay);

	@Query("select m.maMB as maMB, m.loai as loai, count(n.maNV) as soPhiCong "
			+ "from MayBay m, ChungNhan c, NhanVien n where m.maMB = c.MaMB and c.MaNV = n.maNV group by m.maMB, m.loai")
	List<Map<String, Object>> findMayBay();
}
