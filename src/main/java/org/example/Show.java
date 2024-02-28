package org.example;

public class Show {
    static int NUM_FOR_ARRAY = 10; //  배열값 비교를 위한 변수 ex) 10시 --> 0번째 배열, 11시 --> 1번째 배열 ...

    public void Show_List(char[][] reserve_list) {
        System.out.println("\n----- 예약 현황 -----\n| A || B || C |");
        for (int i = 10; i < 23; i++) {
            if (i < 13) {
                System.out.println("오전 " + i + "시| " + reserve_list[0][i - NUM_FOR_ARRAY] + " || " + reserve_list[1][i - NUM_FOR_ARRAY] + " || " + reserve_list[2][i - NUM_FOR_ARRAY] + " |");
            } else {
                int time_12 = i - 12; // 24시간 표기법을 12시간 표기법으로 변환
                String new_time = String.format("%02d", time_12);
                System.out.println("오후 " + new_time + "시| " + reserve_list[0][i - NUM_FOR_ARRAY] + " || " + reserve_list[1][i - NUM_FOR_ARRAY] + " || " + reserve_list[2][i - NUM_FOR_ARRAY] + " |");
            }
        }
        System.out.print("\n");
    }
}
