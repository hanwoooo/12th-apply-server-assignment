package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Reservation reservation = new Reservation();
        Show show = new Show();
        Inquiry inquiry = new Inquiry();

        int menuNum = 0; //메뉴의 선택지 숫자 일단 0으로 초기화 하여 while문 안에서 다시 입력 받도록 한다.

        // A, B, C 스터디 룸의 시간대를 일차원 배열로 나타냄.
        char[][] Room = new char[3][13]; // 0번째 열 = A / 1번째 열 = B / 2번째 열 = C

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 13; j++) {
                Room[i][j] = 'x';
            }
        }

        System.out.println("스터디룸 예약 프로그램입니다.\n");

        while (menuNum != 5) {
            System.out.println("----- 작업 -----\n");
            System.out.println("1. 스터디룸 예약\n2. 예약 현황 조회\n3. 문의 남기기\n4. 문의 리스트 보기\n5. 프로그램 종료\n");
            System.out.print("작업을 선택하세요: ");
            menuNum = scanner.nextInt();

            switch (menuNum) {
                case 1:
                    // 예약 정보 입력
                    reservation.Get_Reservation_Info();
                    // 1. 이름이 틀린 경우
                    if (!reservation.Check_StudyRoom_Name())
                        break;
                    // 2. 운영시간(10시~22시)을 벗어난 시간을 입력한 경우 , 3. 시작시간이 종료시간보다 늦은 시간인 경우
                    if (!reservation.Check_Reservation_Time())
                        break;
                    // 4. 이미 예약이 되어있는 경우 & 예약이 없을시 예약하기
                    if (!reservation.Check_Early_Reservation(Room))
                        break;
                    break;
                case 2:
                    show.Show_List(Room);
                    break;
                case 3:
                    // 문의 아이디, 내용 입력
                    inquiry.Get_Inquiry_Info();
                    // 출력을 위한 문의 내용 저장
                    inquiry.Add_Inquiry_Info();
                    break;
                case 4:
                    inquiry.Show_Inquiry_List();
                    break;
                case 5:
                    System.out.println("\n프로그램을 종료합니다...");
                    break;
            }
        }
    }
}