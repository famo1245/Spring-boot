package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)  //jpa 에서 데이터 변경은 transaction 안에서 일어나야함, public 메서드는 알아서 적용
//@AllArgsConstructor //알아서 생성자 만들어줌
@RequiredArgsConstructor    //final field를 이용해 생성자 만들어줌
public class MemberService {

//    @Autowired  //필드 주입은 단점이 많다, 권장 x
    private final MemberRepository memberRepository;    //final -> compile 시점에 확인 가능, 필수 임을 표시

    /*@Autowired  //setter injection -> 주입을 쉽게한다는 장점, public 이라서 중간에 누가 바꿀 수 있다, 권장 x
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/

//    @Autowired  //constructor injection -> 중간에 바꿀 수 없지만, 보통 생성시 결정, 권장하는 방법, 생성자 하나면 annotation 없어도 됨
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /**
     * 회원 가입
     */
    @Transactional  //쓰기에서 쓰지 마
    public Long join(Member member) {
        validateDuplicateMember(member);    //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());   // 이름에 unique를 걸어라
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");  // 멀티 쓰레드 상황고려, 중복 가능
        }
    }

    /**
     * 회원 전체 조회
     */
//    @Transactional(readOnly = true) //조회에서 최적화 됨, 읽기에서만
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 하나 조회
     */
//    @Transactional(readOnly = true)
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
