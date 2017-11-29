package me_book;

public class Friend {

	private String name;
	private String nickname;
	private String gender;
	private String one_sentence_evaluation; // 一句话评价
	private int age;
	private int point = 50; // 每个人有50个基本分,得分可以为负数，但是当得分为负数时，将显示NOT FOUND (相当于因为被列入了黑名单或者被beat多次)

	Friend(String name) { // name 是沟通Friend类和Me_book类的中间桥梁
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public void increase() {
		this.point += 20;
	}

	public void decrease() {
		this.point -= 20;
	}

	public String getOne_sentence_evaluation() {
		return one_sentence_evaluation;
	}

	public void setOne_sentence_evaluation(String one_sentence_evaluation) {
		this.one_sentence_evaluation = one_sentence_evaluation;
	}

	public void personal_information() {
		if (name != null) {
			System.out.println("NAME\t\t" + name);
		} else {
			System.out.println("NAME\t\tNOT FOUND");
		}
		if (gender != null) {
			System.out.println("GENDER\t\t" + gender);
		} else {
			System.out.println("GENDER\t\tNOT FOUND");
		}
		if (nickname != null) {
			System.out.println("NICKNAME\t\t" + nickname);
		} else {
			System.out.println("NICKNAME\t\tNOT FOUND");
		}
		if (age != 0) {
			System.out.println("AGE\t\t" + age);
		} else {
			System.out.println("AGE\t\tNOT FOUND");
		}
		if (point >= 0) {
			System.out.println("POINT\t\t" + point);
		} else {
			System.out.println("POINT\t\tNOT FOUND");
		}
	}

}
