package project.util;

import java.util.List;
import java.util.stream.IntStream;

public class AppUtils {
	private AppUtils() {
	}
	
	public static <T> List<T> reverse(final List<T> list) {
	    final int last = list.size() - 1;
	    return IntStream.rangeClosed(0, last)
	        .map(i -> (last - i))
	        .mapToObj(list::get)
	        .toList();
	}
}
