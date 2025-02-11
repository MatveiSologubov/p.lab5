import java.util.*;

public class CommandManager {
	private Map<String, Command> commands = new HashMap<>();

	public void addCommand(String name, Command command) {
		commands.put(name, command);
	}
}
