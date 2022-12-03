package visual;

public class WindowProperties {

	private static int height = 500;
	private static int width = 500;
	private static String title = "Fractal";
	private static double ratio = 5 / 4; // width / height

	public static int getHeight() {
		return WindowProperties.height;
	}

	public static void setHeight(int height) {
		WindowProperties.height = height;
	}

	public static int getWidth() {
		return WindowProperties.width;
	}

	public static void setWidth(int width) {
		WindowProperties.width = width;
	}

	public static String getTitle() {
		return WindowProperties.title;
	}

	public static void setTitle(String title) {
		WindowProperties.title = title;
	}

	public static double getRatio() {
		return ratio;
	}

	public static void setRatio(double ratio) {
		WindowProperties.ratio = ratio;
	}

}
