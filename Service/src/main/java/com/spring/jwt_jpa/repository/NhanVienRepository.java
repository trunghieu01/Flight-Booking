package com.spring.jwt_jpa.repository;

import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.jwt_jpa.entity.NhanVien;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, String> {
	@Query(value = "SELECT * FROM nhanvien  WHERE manv = ?1", nativeQuery = true)
	List<NhanVien> findByMaNV(String manv);
	
	@Query(value = "SELECT * FROM nhanvien  WHERE Luong < ?1", nativeQuery = true)
	List<NhanVien> findByLuong(int luong);

	@Query(value = "SELECT sum(Luong) FROM nhanvien", nativeQuery = true)
	double sumLuong();

	@Query("select nv from NhanVien nv, ChungNhan cn, MayBay mb where mb.maMB = cn.MaMB and cn.MaNV = nv.maNV and mb.loai like ?1")
	List<NhanVien> findNhanVienByLoaiMayBay(String loaiMB);

	@Query("select nv from NhanVien nv, ChungNhan cn, MayBay mb where mb.maMB = cn.MaMB and cn.MaNV = nv.maNV and mb.maMB = ?1")
	List<NhanVien> findNhanVienByMa(int ma);

	@Query("select n from NhanVien n where n.maNV in "
			+ "(select c.MaNV from ChungNhan c where c.MaMB in "
			+ "(select m1.maMB  from MayBay m1 where m1.loai like ?1) or c.MaMB in "
			+ "(select m2.maMB  from MayBay m2  where m2.loai like ?2) group by c.MaNV )")
	List<NhanVien> findPhiCong(String a,String b);
	
	@Query("select nv.ten from NhanVien nv, ChungNhan cn, MayBay mb where mb.maMB = cn.MaMB and cn.MaNV = nv.maNV and mb.loai like ?1 group by nv.ten")
	List<String> findTenPhiCong(String loaiMB);
	
	@Query("select n.maNV from NhanVien n, ChungNhan c, MayBay m where n.maNV = c.MaNV and c.MaMB = m.maMB group by c.MaNV having count(c.MaMB) >= 3")
    List<String> findMaNhanVienLai3Loai();
	
	@Query("select n.maNV as maNV, max(m.tamBay) as tamBayLonNhat " +
            "from NhanVien n, ChungNhan c, MayBay m where n.maNV = c.MaNV and c.MaMB = m.maMB group by c.MaNV having count(c.MaMB) >= 3")
    List<Map<String, Object>> findPhiCongLai3LoaiMBVaTamBayMax();
	
	@Query("select n.maNV as maNV, count(m.maMB) as soMayBayLaiDuoc " +
            "from ChungNhan c, MayBay m, NhanVien n where c.MaMB = m.maMB and c.MaNV = n.maNV group by c.MaNV  ")
    List<Map<String, Object>> findPhiCongvaSoMB();
	
	@Query("select n from NhanVien n where n.maNV not in (select c.MaNV from ChungNhan c group by c.MaNV )")
    List<NhanVien> findNhanVien();
	
	@Query("select n.maNV from NhanVien n where n.luong = (select max(n2.luong)from NhanVien n2 )")
    String findMaxLuong();
	
	@Query("select SUM(n.luong) from NhanVien n where n.maNV in (select c.MaNV from ChungNhan c group by c.MaNV )")
	int sumLuongPhiCong();
	
	@Query(value = "UPDATE nhanvien SET Luong = ?1 WHERE MaNV = ?2", nativeQuery = true)
	void updateNhanVien(int luong, String maNV);
}
