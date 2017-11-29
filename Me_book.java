//MeBook可以用来存放和姓名相关的一组信息（比如电话号码或者是邮箱～～）
//Me_book基本的功能是用来存储friend的联系方式，并将其自动分类；当然，就像所有手机的通讯录不仅仅是通讯录这么简单，还可以通过访问Me_book的内置方法来索取索取联系人的详细信息，或者设置比如为其设置一个nickname
//两个类的联系是name
//当Me_book列出1.2.3.4.5之类的选项，如果用户没有输入可选择的选项，Me_book会自动进入循环，知道用户输入正确的指令
package me_book;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Me_book {

	private static Scanner sca = new Scanner(System.in);
	private boolean isOpen = false;
	private int name_number;
	private String password = ""; // 初始化密码为空字符串
	private ArrayList<String> name_list = new ArrayList();
	private ArrayList<String> name_normal = new ArrayList();
	private ArrayList<String> name_star = new ArrayList();
	private ArrayList<String> name_black_list = new ArrayList();
	private HashMap<String, Friend> friend_map = new HashMap<>();
	private HashMap<String, String> name_informations = new HashMap<>();

	static Me_book me_book = new Me_book();

	void launch() {
		if (!isOpen) {
			for (int i = 1; i <= 3; ++i) {
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					System.out.println("SHIT");
				}
				System.out.print("PASSWORD:");
				String input_password = sca.nextLine();
				if (input_password.equals(password)) {
					String opening = "ME BOOK IS LAUNCHING";
					char[] openings = opening.toCharArray();
					for (int j = 0; j < 20; ++j) {
						System.out.print(openings[j]);
						try {
							Thread.sleep(1000);
						} catch (Exception e) {
							System.out.println("SHIT");
						}
					}

					System.out.println("ME BOOK ALREADY LAUNCH");
					isOpen = true;
					System.out.println("WELCOME TODAY is " + new Date().toString());
					return;
				} else {
					if (i != 0) {
						System.out.println("NOT CORRECT " + (3 - i) + " CHANCE LEFT");
					} else {
						System.out.println("ME BOOK SLEEP PLEASE WAIT");
					}
				}
			}
		} else {
			System.out.println("ALREADY OPEN");
		}
	}

	void shutdown() {
		if (isOpen) {
			System.out.println("SHUTING DOWN PLEASE WAIT 20 SECONDS");
			long start = System.currentTimeMillis();
			for (int i = 1; i <= 20; ++i) {
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					System.out.println("SHIT");
				}
				System.out.println(i + " SECOND PASSED");
			}
			isOpen = !isOpen;
			System.out.println("ME BOOK HAS BEEN SHUTDOWN");
		} else {
			System.out.println("ALREADY SHUTDOWN");
		}
	}

	void add() {
		if (isOpen) {
			System.out.print("NAME ");
			String name = sca.nextLine();
			if (!name_list.contains(name)) {
				name_list.add(name);
				System.out.print("INFORMATION ");
				String information = sca.nextLine();
				name_informations.put(name, information);

				friend_map.put(name, new Friend(name)); // 这一步很关键,形成了依赖关系

				if (information.matches("\\d+")) { // 可以自动识别用户输入的信息是电话号码还是邮箱
					System.out.println("CLASSIFIED INTO PHONE NUMBER");
				} else if (information.matches("[\\w\\.]+@[\\w\\.]+\\.\\w+")) {
					System.out.println("CLASSIFIED INTO E-MAIL");
				} else {
					System.out.println("UNCLASSIFIED");
				}
				// else if (information.matches("[a-zA-Z\\.,?!~:]")) {
				// System.out.println("CLASSIFIED INTO DESCRIPTION");
				// }
				name_number++;

				System.out.println("EXTRA INFORMATION ADD (y/n)"); // 可选的额外信息
				String input_choice = sca.nextLine();
				if (input_choice.equals("y")) {
					set_extra_information(name);
					System.out.println("EXTRA INFORMATION HAS ALREADY BEEN SET");
				} else {
					System.out.println("EXTRA INFORMATION HAS NOT BEEN SET");
					return;
				}
			} else {
				System.out.println("ALREADY EXISTED");
			}
		} else {
			System.out.println("ALREADY SHUTDOWN");
		}
	}

	void show_single() {
		if (isOpen) {
			System.out.println("PLEASE ENTER NAME ");
			String chosen_name = sca.nextLine();
			try {
				if (name_informations.get(chosen_name).matches("\\d+")) {
					System.out.println("His/her phone number is " + name_informations.get(chosen_name));
				} else if (name_informations.get(chosen_name).matches("[\\w\\.]+@[\\w\\.]+\\.\\w+")) {
					System.out.println("His/her e-mail is " + name_informations.get(chosen_name));
				} else {
					System.out.println("His/her information is" + name_informations.get(chosen_name));
				}
			} catch (Exception e) {
				System.out.println("NOT FOUND");
			}
		} else {
			System.out.println("ALREADY SHUTDOWN");
		}
	}

	void show_single(String name) {
		if (isOpen) {
			try {
				if (name_informations.get(name).matches("\\d+")) {
					System.out.println("His/her phone number is " + name_informations.get(name));
				} else if (name_informations.get(name).matches("[\\w\\.]+@[\\w\\.]+\\.\\w+")) {
					System.out.println("His/her e-mail is " + name_informations.get(name));
				} else {
					System.out.println("His/her information is" + name_informations.get(name));
				}
			} catch (Exception e) {
				System.out.println("NOT FOUND");
			}
		} else {
			System.out.println("ALREADY SHUTDOWN");
		}
	}

	void show_all() {
		if (isOpen) {
			System.out.println("====================");
			Set set = name_informations.entrySet();
			Iterator it = set.iterator();
			System.out.println("NAME\tINFORMATION");
			while (it.hasNext()) {
				Map.Entry<String, Integer> me = (Map.Entry<String, Integer>) it.next();
				System.out.println(me.getKey() + "\t" + me.getValue());
			}
			System.out.println("====================");
		} else {
			System.out.println("ALREADY SHUTDOWN");
		}
	}

	void show_format() {
		if (isOpen) {
			Set set = name_informations.entrySet();
			Iterator it = set.iterator();
			while (true) {
				System.out.println("1.phone_number  2.e-mail");
				String format = sca.nextLine();
				System.out.println("====================");
				if (format.equals("phone_number") || format.equals("1")) {
					System.out.println("NAME\tPHONE NUMBER");
					while (it.hasNext()) {
						Map.Entry<String, Integer> me = (Map.Entry<String, Integer>) it.next();
						if (String.valueOf(me.getValue()).matches("\\d+")) {
							System.out.println(me.getKey() + "\t" + me.getValue());
						}
					}
					System.out.println("====================");
					break;
				} else if (format.equals("e-mail") || format.equals("2")) {
					System.out.println("NAME\tE-MAIL");
					while (it.hasNext()) {
						Map.Entry<String, Integer> me = (Map.Entry<String, Integer>) it.next();
						if (String.valueOf(me.getValue()).matches("[\\w\\.]+@[\\w\\.]+\\.\\w+")) {
							System.out.println(me.getKey() + "\t" + me.getValue());
						}
					}
					System.out.println("====================");
					break;
				} else {
					System.out.println("NOT FOUND");
				}
			}
		} else {
			System.out.println("ALREADY SHUTDOWN");
		}
	}

	void show() {
		if (isOpen) {
			while (true) {
				System.out.println("1.show_single  2.show_all  3.show_format  4.person_number");
				String input_show = sca.nextLine();
				if (input_show.equals("show_single") || input_show.equals("1")) {
					show_single();
					break;
				} else if (input_show.equals("show_all") || input_show.equals("2")) {
					show_all();
					break;
				} else if (input_show.equals("show-format") || input_show.equals("3")) {
					show_format();
					break;
				} else if (input_show.equals("person_number") || input_show.equals("4")) {
					person_number();
					break;
				} else {
					System.out.println("NOT FOUND");
				}
			}
		} else {
			System.out.println("ALREADY SHUTDOWN");
		}
	}

	void person_number() {
		if (isOpen) {
			System.out.println("ALREADY HAS " + name_number + " PERSON");
		} else {
			System.out.println("ALREADY SHUTDOWN");
		}

	}

	void put_class() {
		if (isOpen) {
			while (true) {
				System.out.println("1.normal  2.star  3.black_list");
				String input_class = sca.nextLine();
				if (input_class.equals("normal") || input_class.equals("1")) {
					System.out.print("NORMAL FRIEND ADD ");
					String add_name = sca.nextLine();
					if (name_list.contains(add_name)) {
						name_normal.add(add_name);
						System.out.println("PUT SUCCESS");
						break;
					} else {
						System.out.println("NOT FOUND");
						break;
					}
				} else if (input_class.equals("star") || input_class.equals("2")) {
					System.out.print("STAR FRIEND ADD ");
					String add_name = sca.nextLine();
					if (name_list.contains(add_name)) {
						name_star.add(add_name);

						friend_map.get(add_name).increase(); // 当被put进star好友后，好感度会增加

						System.out.println("PUT SUCCESS");
						break;

					} else {
						System.out.println("NOT FOUND");
						break;
					}
				} else if (input_class.equals("black_list") || input_class.equals("3")) {
					System.out.print("BLACK LIST ADD ");
					String add_name = sca.nextLine();
					if (name_list.contains(add_name)) {
						name_black_list.add(add_name);

						friend_map.get(add_name).decrease(); // 当被put进黑名单后，好感度自然会下降

						System.out.println("PUT SUCCESS");
						break;

					} else {
						System.out.println("NOT FOUND");
						break;
					}
				} else {
					System.out.println("NOT FOUND");
				}
			}
		} else {
			System.out.println("ALREADY SHUTDOWN");
		}
	}

	void set_class(String name) { // 这是比较难的部分，因为不仅要在新的列表中添加元素还要在原来的类标中删除相应的元素这就要获取相应元素的下标
		System.out.println("1.normal  2.star  3.black_list");
		String input_class = sca.nextLine();
		if (input_class.equals("normal") || input_class.equals("1")) {
			if (name_list.contains(name)) {
				if (!name_normal.contains(name)) {
					name_normal.add(name);
					System.out.println("SET SUCCESS");
					if (name_star.contains(name)) {
						name_star.remove(name_star.indexOf(name));
					} else if (name_black_list.contains(name)) {
						name_black_list.remove(name_black_list.indexOf(name));
					} else {
						System.out.println("SHIT");
					}
				} else {
					System.out.println("ALREADY EXISTED");
				}

			} else {
				System.out.println("NOT FOUND");
			}
		} else if (input_class.equals("star") || input_class.equals("2")) {
			if (name_list.contains(name)) {
				if (!name_star.contains(name)) {
					name_star.add(name);
					System.out.println("SET SUCCESS");
					friend_map.get(name).increase(); // 当被put进star好友后，好感度会增加
					if (name_normal.contains(name)) {
						name_normal.remove(name_normal.indexOf(name));
					} else if (name_black_list.contains(name)) {
						name_black_list.remove(name_black_list.indexOf(name));
					} else {
						System.out.println("SHIT");
					}
				} else {
					System.out.println("ALREADY EXISTED");
				}
			} else {
				System.out.println("NOT FOUND");
			}
		} else if (input_class.equals("black_list") || input_class.equals("3")) {
			if (name_list.contains(name)) {
				if (!name_black_list.contains(name)) {
					name_black_list.add(name);
					System.out.println("SET SUCCESS");
					friend_map.get(name).decrease(); // 当被put进黑名单后，好感度自然会下降
					if (name_star.contains(name)) {
						name_star.remove(name_star.indexOf(name));
					} else if (name_normal.contains(name)) {
						name_normal.remove(name_normal.indexOf(name));
					} else {
						System.out.println("SHIT");
					}
				} else {
					System.out.println("ALREADY EXISTED");
				}

			} else {
				System.out.println("NOT FOUND");
			}
		} else {
			System.out.println("NOT FOUND");
		}

	}

	void show_class() {
		System.out.println("1.normal  2.star  3.black_list");
		String input_class = sca.nextLine();
		if (input_class.equals("normal") || input_class.equals("1")) {
			if (name_normal.size() != 0) {
				System.out.println(name_normal);
			} else {
				System.out.println("NOT FOUND");
			}

		} else if (input_class.equals("star") || input_class.equals("2")) {
			if (name_star.size() != 0) {
				System.out.println(name_star);
			} else {
				System.out.println("NOT FOUND");
			}
		} else if (input_class.equals("black") || input_class.equals("3")) {
			if (name_black_list.size() != 0) {
				System.out.println(name_black_list);
			} else {
				System.out.println("NOT FOUND");
			}
		}
	}

	void set_information() {
		System.out.print("SET NAME ");
		String set_name = sca.nextLine();
		if (name_list.contains(set_name)) {
			System.out.print("NEW INFORMATION ");
			String set_information = sca.nextLine();
			name_informations.put(set_name, set_information);
			System.out.println("INFORMATION CHANGED");
		} else {
			System.out.println("NOT FOUND");
		}
	}

	void set_information(String name) { // overload
		if (name_list.contains(name)) {
			System.out.print("NEW INFORMATION ");
			String set_information = sca.nextLine();
			name_informations.put(name, set_information);
			System.out.println("INFORMATION CHANGED");
		} else {
			System.out.println("NOT FOUND");
		}
	}

	void set_password() {
		if (isOpen) {
			for (int i = 1; i <= 3; ++i) {
				System.out.print("OLD PASSWORD ");
				String input_password = sca.nextLine();
				if (input_password.equals(password)) {
					System.out.print("NEW PASSWORD");
					String input_new_password = sca.nextLine();
					this.password = input_new_password;
					System.out.println("PASSWORD HAS ALREADY CHANGED");
					return;
				} else {
					System.out.println("NOT CORRECT " + (3 - i) + " CHANCE LEFT");
				}
			}
		} else {
			System.out.println("ALREADY SHUTDOWN");
		}
	}

	void set() { // 封装，将来两个set方法进行封装(在非ANM模式下的set方法只能访问basic_information基本信息)
		if (isOpen) {
			while (true) {
				System.out.println("1.set_information  2.set_password");
				String input = sca.nextLine();
				if (input.equals("set_information") || input.equals("1")) {
					set_information();
					break;
				} else if (input.equals("set_password") || input.equals("2")) {
					set_password();
					break;
				} else {
					System.out.println("NOT FOUND");
				}
			}
		} else {
			System.out.println("ALREADY SHUTDOWN");
		}
	}

	void set_extra_information(String name) { // 这是为Friend类的每一个对象设置的额外信息(因为extra_information属于personal_information，所以必须在ANM模式下才能访问（或者在add
												// Friend时进行信息的初始化），普通模式下只能访问basic_information)
		System.out.print("(NEW) GENDER ");
		String input_gender = sca.nextLine();
		friend_map.get(name).setGender(input_gender);
		System.out.print("(NEW) NICK NMAE ");
		String input_nickname = sca.nextLine();
		friend_map.get(name).setNickname(input_nickname);
		System.out.print("(NEW) AGE ");
		String input_age = sca.nextLine();
		friend_map.get(name).setAge(Integer.valueOf(input_age));
	}

	void hug() {
		System.out.print("HUG WHOM ");
		String name_hug = sca.nextLine();
		if (name_list.contains(name_hug)) {
			int tmp = 0;
			int ran = (int) (Math.random() * 4);
			tmp = ran;
			for (int i = 1; i <= ran; ++i) { // Random，增加可扩展性
				friend_map.get(name_hug).increase();
			}
			System.out.println("POINT INCREASED " + (20 * tmp));
		} else {
			System.out.println("NOT FOUND");
		}
	}

	void hug(String name) {
		if (name_list.contains(name)) {
			int tmp = 0;
			int ran = (int) (Math.random() * 4);
			tmp = ran;
			for (int i = 1; i <= ran; ++i) { // Random，增加可扩展性
				friend_map.get(name).increase();
			}
			System.out.println("POINT INCREASED " + (20 * tmp));
		} else {
			System.out.println("NOT FOUND");
		}
	}

	void beat() {
		System.out.print("BEAT WHOM ");
		String name_hug = sca.nextLine();
		if (name_list.contains(name_hug)) {
			int tmp = 0;
			int ran = (int) (Math.random() * 4);
			tmp = ran;
			for (int i = 1; i <= ran; ++i) { // Random，增加可扩展性
				friend_map.get(name_hug).increase();
			}
			System.out.println("POINT DECREASED " + (20 * tmp));
		} else {
			System.out.println("NOT FOUND");
		}
	}

	void beat(String name) {
		if (name_list.contains(name)) {
			int tmp = 0;
			int ran = (int) (Math.random() * 4);
			tmp = ran;
			for (int i = 1; i <= ran; ++i) { // Random，增加可扩展性
				friend_map.get(name).decrease();
			}
			System.out.println("POINT DECREASED " + (20 * tmp));
		}
	}

	void one_sentence_evaluation(String name) { // parameter确定了其只能被ANM模式访问
		System.out.println("1.set  2.see");
		String input = sca.nextLine();
		if (input.equals("set") || input.equals("1")) {
			System.out.print("ONE SENTENEC EVALUATION ");
			String one_sentence_evaluation = sca.nextLine();
			friend_map.get(name).setOne_sentence_evaluation(one_sentence_evaluation);
		} else if (input.equals("set") || input.equals("2")) {
			if (friend_map.get(name).getOne_sentence_evaluation() != null) {
				System.out.println(friend_map.get(name).getOne_sentence_evaluation());
			} else {
				System.out.println("NOT FOUND");
			}
		}
	}

	void personal_information() {
		System.out.print("WHOSE INFORMATION ");
		String name_information = sca.nextLine();
		if (name_list.contains(name_information)) {
			friend_map.get(name_information).personal_information();
		} else {
			System.out.println("NOT FOUND");
		}
	}

	void social() {
		if (isOpen) {
			while (true) {
				System.out.println("1.put_class  2.show_class  3.hug  4.beat  5.personal_information");
				String input_social = sca.nextLine();
				if (input_social.equals("put_class") || input_social.equals("1")) {
					put_class();
					break;
				} else if (input_social.equals("show_class") || input_social.equals("2")) {
					show_class();
					break;
				} else if (input_social.equals("hug") || input_social.equals("3")) {
					hug();
					break;
				} else if (input_social.equals("beat") || input_social.equals("4")) {
					beat();
					break;
				} else if (input_social.equals("personal_information") || input_social.equals("5")) {
					personal_information();
					break;
				} else {
					System.out.println("NOT FOUND");
				}
			}
		} else {
			System.out.println("ALREADY SHUTDOWN");
		}
	}

	void show_ANM(String name) {
		System.out.println("1.show_basic_information  2.show_personal_information  3.show_class");
		String input_ANM = sca.nextLine();
		if (input_ANM.equals("show_basic_information") || input_ANM.equals("1")) {
			show_single(name);
		} else if (input_ANM.equals("show_personal_information") || input_ANM.equals("2")) {
			friend_map.get(name).personal_information();
		} else if (input_ANM.equals("show_class") || input_ANM.equals("3")) {
			if (name_normal.contains(name)) {
				System.out.println("NORMAL FRIEND");
			} else if (name_star.contains(name)) {
				System.out.println("STAR FRIEND");
			} else if (name_black_list.contains(name)) {
				System.out.println("BLACK LIST RELAIONSHIP");
			}
		} else {
			System.out.println("NOT FOUND");
		}
	}

	void set_ANM(String name) {
		System.out.print("1.set_basic_information  2.set_personal_information  3.set_class");
		String input_ANM = sca.nextLine();
		if (input_ANM.equals("set_basic_information") || input_ANM.equals("1")) {
			set_information(name);
		} else if (input_ANM.equals("set_personal_information") || input_ANM.equals("2")) {
			set_extra_information(name);
		} else if (input_ANM.equals("set_class") || input_ANM.equals("3")) {
			set_class(name);
		} else {
			System.out.println("NOT FOUND");
		}
	}

	void social_ANM(String name) {
		System.out.println("1.hug  2.beat  3.one_sentence_evaluation"); // one_sentence_evaluation只有在ANM模式下才能看见
		String input_ANM = sca.nextLine();
		if (input_ANM.equals("hug") || input_ANM.equals("1")) {
			hug(name);
		} else if (input_ANM.equals("beat") || input_ANM.equals("2")) {
			beat(name);
		} else if (input_ANM.equals("one_sentence_evaluation") || input_ANM.equals("3")) {
			one_sentence_evaluation(name);
		} else {
			System.out.println("NOT FOUND");
		}
	}

	void according_name_mode() { // 你必须先结束当前friend的ANM模式，然后才能结束总的ANM模式
									// ANM模式拥有更大的访问权限，可以访问私人信息并且用户能够在ANM模式下对相应的Friend进行一句话描述（是Friend对象的属性字段）

		if (isOpen) {
			while (true) {
				System.out.print("ANM START\nANM NAME ");
				String name = sca.nextLine();
				while (true) {
					if (name_list.contains(name)) {
						System.out.println("1.show  2.set  3.soclal");
						String input_choice = sca.nextLine();
						if (input_choice.equals("show") || input_choice.equals("1")) {
							show_ANM(name);
						} else if (input_choice.equals("set") || input_choice.equals("2")) {
							set_ANM(name);
						} else if (input_choice.equals("social") || input_choice.equals("3")) {
							social_ANM(name);
						}
						System.out.println("KILL ANM FOR THIS FRIEND (y/n)");
						String progress_ANM_this = sca.nextLine();
						if (progress_ANM_this.equals("y")) {
							System.out.println("ANM FOR THIS FRIEND ALREADY KILLED");
							break;
						} else if (progress_ANM_this.equals("n")) {
							System.out.println("ANM IS STILL RUNNING");
						} else {
							System.out.println("NOT FOUND STILL RUNNING");
						}
					} else {
						System.out.println("NOT FOUND\n(NEW) ANM NAME");
						String new_name = sca.nextLine();
						new_name = name;
					}
				}
				System.out.print("KILL ANM (y/n)");
				String progress_ANM = sca.nextLine();
				if (progress_ANM.equals("y")) {
					System.out.println("ANM ALREADY KILLED");
					return;
				} else if (progress_ANM.equals("n")) {
					System.out.println("ANM IS STILL RUNNING");
				} else {
					System.out.println("NOT FOUND STILL RUNNING");
				}
			}
		} else {
			System.out.println("ALREADY SHUTDOWN");
		}
	}

	public static void main(String[] args) {
		System.out.println(
				"\t\t\t\t\tMEBOOK\n\"help\" for more assistance\n=============================================");
		while (true) {
			String order = sca.nextLine();
			if (order.equals("ANM") || order.equals("0")) {
				me_book.according_name_mode();
			} else if (order.equals("launch") || order.equals("1")) {
				me_book.launch();
			} else if (order.equals("shutdown") || order.equals("2")) {
				me_book.shutdown();
			} else if (order.equals("add") || order.equals("3")) {
				me_book.add();
			} else if (order.equals("show") || order.equals("4")) {
				me_book.show();
			} else if (order.equals("set") || order.equals("5")) {
				me_book.set();
			} else if (order.equals("social") || order.equals("6")) {
				me_book.social();
			} else if (order.equals("help")) {
				System.out.println("0.ANM  1.launch  2.shutdown  3.add  4.show  5.set  6.social"); // 只有ANM模式是循环模式，当且仅当退出当前好友后才能退出ANM模式（用名字进行检索）
			} else {
				System.out.println("NOT FOUND");
			}
		}

	}

}
