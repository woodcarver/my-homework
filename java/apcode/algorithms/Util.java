package algorithms;

import java.util.List;

public class Util {
	public static <T> void printListList(List<List<T>> lists) {
		if (lists == null) {
			System.out.println("The lists is null.");
			return;
		}
		System.out.print("{\n");
		for (int i = 0, len = lists.size(); i < len; i++) {
			System.out.print("\t");
			Util.printList(lists.get(i));
		}
		System.out.print("}\n");
	}
	public static <T> void printList(List<T> list) {
		if (list == null) {
			System.out.println("The list is null.");
			return;
		}
		System.out.print("[");
		for (int i = 0, len = list.size(); i < len; i++) {
			System.out.print(list.get(i) + ",");
		}
		System.out.print("]\n");
	}
}
