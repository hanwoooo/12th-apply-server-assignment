package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int NUM_FOR_ARRAY = 10; //  배열값 비교를 위한 변수 ex) 10시 --> 0번째 배열, 11시 --> 1번째 배열 ...

    public static void Show_Reserve_List(char[][] reserve_list) {
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
        return;
    }

    public static void main(String[] args) {
        int menuNum = 0; //메뉴의 선택지 숫자 일단 0으로 초기화 하여 while문 안에서 다시 입력 받도록 한다.

        char studyRoom; // 예약할 스터디룸
        int startTime; // 사용 시작 시간
        int endTime; // 사용 종료 시간

        String queryID = ""; // 문의 아이디
        String queryDetail = ""; // 문의 내용
        String[] ID_list = new String[1]; // 문의 아이디 리스트
        String[] detail_list = new String[1]; // 문의 내용 리스트

        // A, B, C 스터디 룸의 시간대를 일차원 배열로 나타냄.
        char[][] Room = new char[3][13]; // 0번째 열 = A / 1번째 열 = B / 2번째 열 = C

        Scanner scanner = new Scanner(System.in);

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
                    int reserve_count = 0; // 입력한 시간대의 예약 확인을 위한 변수
                    int Room_type; // 선택한 스터디룸이 A인지 B인지 C인지 확인하는 변수 A = 0, B = 1, C = 2
                    System.out.println("\n----- 스터디룸 예약 -----");
                    System.out.print("예약할 스터디룸: ");
                    studyRoom = scanner.next().charAt(0);
                    System.out.print("사용 시작 시간: ");
                    startTime = scanner.nextInt();
                    System.out.print("사용 종료 시간: ");
                    endTime = scanner.nextInt();

                    // -------------------- 예약이 불가한 경우들 --------------------
                    // 1. 스터디룸 이름이 틀린 경우
                    if (studyRoom != 'A' && studyRoom != 'B' && studyRoom != 'C') {
                        System.out.println("\n스터디룸 " + studyRoom + "는 존재하지 않습니다.\n예약에 실패했습니다.\n");
                        break;
                    }
                    // 2. 스터디룸 운영시간(10시~22시)를 벗어난 시간을 입력한 경우
                    if (startTime < 10 || endTime < 10 || startTime > 22 || endTime > 22){
                        System.out.println("\n시작시간과 종료시간은 10시~22시사이의 시간이어야 합니다!\n");
                        break;
                    }
                    // 3. 스터디룸 시작시간이 종료시간보다 늦은 시간인 경우
                    if (startTime > endTime) {
                        System.out.println("\n시작시간이 종료시간보다 늦을 수 없습니다.\n예약에 실패했습니다.\n");
                        break;
                    }
                    // 4. 이미 예약이 되어있는 경우 & 예약이 없을시 예약하기

                    // 스터디룸 정하기
                    if (studyRoom == 'A') {
                        Room_type = 0;
                    } else if (studyRoom == 'B') {
                        Room_type = 1;
                    } else {
                        Room_type = 2;
                    }

                    // 예약유무 확인
                    for (int i = startTime - NUM_FOR_ARRAY; i < endTime - NUM_FOR_ARRAY; i++) {
                        if (Room[Room_type][i] == 'O') {
                            reserve_count++;
                        }
                    }

                    if (reserve_count != 0) { // 예약 되어있는 경우
                        System.out.println("\n스터디룸 " + studyRoom + "는 해당 시간에 이미 예약되어 있습니다.\n예약에 실패했습니다.\n");
                        break;
                    } else {
                        for (int i = startTime - NUM_FOR_ARRAY; i <= endTime - NUM_FOR_ARRAY; i++) {
                            Room[Room_type][i] = 'O';
                        }
                    }
                    System.out.println("\n예약이 완료되었습니다!\n");
                    break;
                case 2:
                    Show_Reserve_List(Room);
                    break;
                case 3:
                    System.out.println("\n----- 문의 남기기 -----");
                    System.out.print("문의 아이디: ");
                    queryID = scanner.next();
                    scanner.nextLine(); // nextLine() 메소드를 통해 next()에서 없어지지 않은 '\n'을 없애주기
                    System.out.print("문의 내용: ");
                    queryDetail = scanner.nextLine();

                    ArrayList<String> new_ID_list = new ArrayList<String>(Arrays.asList(ID_list));
                    ArrayList<String> new_detail_list = new ArrayList<String>(Arrays.asList(detail_list));
                    new_ID_list.remove(null);
                    new_detail_list.remove(null);
                    new_ID_list.add(queryID);
                    new_detail_list.add(queryDetail);
                    ID_list = new_ID_list.toArray(new String[0]);
                    detail_list = new_detail_list.toArray(new String[0]);

                    System.out.println("\n문의가 저장되었습니다!\n");
                    break;
                case 4:
                    System.out.println("\n----- 문의 리스트 보기 -----");
                    if (ID_list[0] == null) {
                        System.out.println("\n문의사항이 없습니다. 문의 남기기를 통해 문의해주세요!\n");
                    } else {
                        for (int i = 0; i < ID_list.length; i++) {
                            System.out.println("문의 아이디:" + ID_list[i]);
                            System.out.println("문의 내용: " + detail_list[i] + "\n");
                        }
                    }
                    break;
                case 5:
                    System.out.println("\n프로그램을 종료합니다...");
                    break;
            }
        }
    }
}