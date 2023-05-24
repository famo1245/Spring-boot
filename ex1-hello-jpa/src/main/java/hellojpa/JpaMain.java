package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        try {

//            Member member = new Member();
//            member.setName("helloA");
//            member.setId(1L);
//            em.persist(member); //db에 저장
//            tx.commit();    //transaction 내에서 crud가 실행되어야 함

            //조회, 수정
//            Member findMember = em.find(Member.class, 1L);  //단순한 조회
//            findMember.setName("helloJPA"); //수정시 저장 안해도 됨
            /*List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .getResultList();*/  //jpql 전체 조회
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(5)
//                    .setMaxResults(8)
//                    .getResultList();
//
//            for (Member member : result) {
//                System.out.println("member.getName() = " + member.getName());
//            }

//            Member member = new Member();
//            member.setId(101L);
//            member.setName("HelloJPA"); // member는 비영속 상태

            //영속, 이때 db에 저장되지 않음
//            em.persist(member);
            //DB에서 조회, 1차 캐시에 저장됨
//            Member findMember1 = em.find(Member.class, 101L);
//            //1차 캐시에서 조회
//            Member findMember2 = em.find(Member.class, 101L);
////            System.out.println("findMember.id = " + findMember.getId());
////            System.out.println("findMember.name = " + findMember.getName());
//            System.out.println("result" + (findMember1 == findMember2)); //jpa가 영속 엔티티의 동일성을 보장해줌

            //영속
//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "B");
//
//            em.persist(member1);
//            em.persist(member2);
//
//            System.out.println("======================");

//            Member member = em.find(Member.class, 150L);    //1차 캐시에 없으므로 영속성 컨텍스트가 됨
//            member.setName("AAAAA"); //java collection 다루듯이 함, persist 할 필요 없음 -> jpa가 알아서 하므로 안 하는게 맞다

//            em.detach(member);  //준영속 상태, JPA가 관리 안함, 특정 엔티티만
//            em.clear(); //영속성 컨텍스트를 완전히 초기화

//            Member member2 = em.find(Member.class, 150L);

//            Member member = new Member(200L, "member200");
//            em.persist(member);

//            em.flush(); //직접 저장을 보고 싶음, 이 시점에서 insert 가 일어남 바로 db에 반영, 1차 캐시는 유지 됨

//            System.out.println("======================");

            /*Member member1 = new Member();
            member1.setUsername("A");
            Member member2 = new Member();
            member2.setUsername("B");
            Member member3 = new Member();
            member3.setUsername("C");
            System.out.println("===================");*/

            // DB_SEQ = 1 | 1
            // DB_SEQ = 51 | 2
            // DB_SEQ = 51 | 3

            /*em.persist(member1); //IDENTITY 전략은 이때 db에 저장, pk 값을 모르기 때문 -> 버퍼링 불가
            //SEQUENCE 1, 51
            em.persist(member2);    //MEM
            em.persist(member3);    //MEM -> 51이 되어야 자동으로 call next value 함

            //SEQUENCE 전략은 이때 값을 가져옴, 버퍼링 가능
            System.out.println("member1.getId() = " + member1.getId());   //persist 시점에 id를 알아옴
            System.out.println("member2.getId() = " + member2.getId());
            System.out.println("member3.getId() = " + member3.getId());
            System.out.println("===================");*/
            
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setName("member1");
//            member.setTeamId(team.getId());
//            member.changeTeam(team);   //jpa에서 알아서 fk로 사용

            team.addMember(member);

            em.persist(member);

            //insert query를 보고 싶을 때
            /*em.flush();
            em.clear();*/

            Member findMember = em.find(Member.class, member.getId());

            List<Member> members = findMember.getTeam().getMembers();

            System.out.println("===================");
            for (Member m : members) {
                System.out.println("m = " + m.getName());
            }
            System.out.println("===================");

            /*Long findTeamId = findMember.getTeamId();
            Team findTeam = em.find(Team.class, findTeamId);*/
            /*Team findTeam = findMember.getTeam();
            System.out.println("findTeam.getName() = " + findTeam.getName());*/

            tx.commit();    //jpa가 commit 전에 관리함
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        //application terminate
        emf.close();
    }
}
