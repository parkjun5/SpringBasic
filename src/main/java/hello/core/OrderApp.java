package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();
        Long memberId = 1L;
        Member member1 = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member1);
        Member member = memberService.findMember(memberId);
        Order coke = orderService.createOrder(member.getId(), "coke", 1500);

        System.out.println(coke.toString());
        System.out.println(coke.calculatePrice());

    }
}
