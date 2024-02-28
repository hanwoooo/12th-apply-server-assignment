package org.example;

import java.util.Scanner;

public class Reservation {
    Scanner scanner = new Scanner(System.in);
    static int NUM_FOR_ARRAY = 10; //  배열값 비교를 위한 변수 ex) 10시 --> 0번째 배열, 11시 --> 1번째 배열 ...

    char studyRoom; // 예약할 스터디룸
    int startTime; // 사용 시작 시간
    int endTime; // 사용 종료 시간
    int reserve_count = 0; // 입력한 시간대의 예약 확인을 위한 변수
    int Room_type; // 선택한 스터디룸이 A인지 B인지 C인지 확인하는 변수 A = 0, B = 1, C = 2

    public void Get_Reservation_Info() {
        System.out.println("\n----- 스터디룸 예약 -----");
        System.out.print("예약할 스터디룸: ");
        studyRoom = scanner.next().charAt(0);
        System.out.print("사용 시작 시간: ");
        startTime = scanner.nextInt();
        System.out.print("사용 종료 시간: ");
        endTime = scanner.nextInt();

        // 스터디룸 정하기
        if (studyRoom == 'A') {
            Room_type = 0;
        } else if (studyRoom == 'B') {
            Room_type = 1;
        } else {
            Room_type = 2;
        }
    }

    public boolean Check_StudyRoom_Name() {
        // 1. 스터디룸 이름이 틀린 경우
        if (studyRoom != 'A' && studyRoom != 'B' && studyRoom != 'C') {
            System.out.println("\n스터디룸 " + studyRoom + "는 존재하지 않습니다.\n예약에 실패했습니다.\n");
            return false;
        }
        return true;
    }

    public boolean Check_Reservation_Time() {
        // 2. 운영시간(10시~22시)을 벗어난 시간을 입력한 경우
        if (startTime < 10 || endTime < 10 || startTime > 22 || endTime > 22) {
            System.out.println("\n시작시간과 종료시간은 10시~22시사이의 시간이어야 합니다!\n");
            return false;
        }
        // 3. 시작시간이 종료시간보다 늦은 시간인 경우
        if (startTime > endTime) {
            System.out.println("\n시작시간이 종료시간보다 늦을 수 없습니다.\n예약에 실패했습니다.\n");
            return false;
        }
        return true;
    }

    public boolean Check_Early_Reservation(char[][] Room) {
        // 4. 이미 예약이 되어있는 경우 & 예약이 없을시 예약하기

        // 예약유무 확인
        for (int i = startTime - NUM_FOR_ARRAY; i < endTime - NUM_FOR_ARRAY; i++) {
            if (Room[Room_type][i] == 'O') {
                reserve_count++;
            }
        }

        if (reserve_count != 0) { // 예약 되어있는 경우
            System.out.println("\n스터디룸 " + studyRoom + "는 해당 시간에 이미 예약되어 있습니다.\n예약에 실패했습니다.\n");
            return false;
        } else {
            for (int i = startTime - NUM_FOR_ARRAY; i <= endTime - NUM_FOR_ARRAY; i++) {
                Room[Room_type][i] = 'O';
            }
        }
        System.out.println("\n예약이 완료되었습니다!\n");
        return true;
    }
}
