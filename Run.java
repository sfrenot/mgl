
public class Run {

	public static void main(String[] args) {

		YoutubeMember Hamid = new YoutubeMember();
		YoutubeChannel Falouja = new YoutubeChannel();
		Falouja.addSubscriber(Hamid);

		Falouja.notifySubscribers(); // Falouja sort une nouvelle video
	}
}
