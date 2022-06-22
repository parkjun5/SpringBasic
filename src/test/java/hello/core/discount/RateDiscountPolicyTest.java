package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10퍼센트 세일이 적용되어야 합니다.")
    void vip_o() {
        //given
        Member member = new Member(1L, "memeber VIP ", Grade.VIP);
        //when
        int discount = rateDiscountPolicy.discount(member, 10000);
        //then
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 없어야한다")
    void vip_x() {

        //given
        Member member = new Member(2L, "memeber Basic ", Grade.BASIC);
        //when
        int discount = rateDiscountPolicy.discount(member, 10000);
        //then
        assertThat(discount).isEqualTo(0);
    }
}