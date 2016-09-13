package practice;

/**
 * Created by zhangguijiang on 16/6/13.
 */
public class SpaceWiper {
    public static void main(String[] args) {
        System.out.print(SpaceWiper.wipeOffSpace("    "));
        System.out.print(SpaceWiper.wipeOffSpace("1111"));
        System.out.print(SpaceWiper.wipeOffSpace(" 2222"));
        System.out.print(SpaceWiper.wipeOffSpace("   3333  "));
        System.out.print(SpaceWiper.wipeOffSpace("4444   "));
        System.out.print(SpaceWiper.wipeOffSpace("  5555  5555"));
        System.out.print(SpaceWiper.wipeOffSpace("6666    6666"));
    }

    private static String wipeOffSpace(String input) {
        if (input != null && input.length() > 0 && !input.isEmpty()) {
            int index = 0;
            char[] inputArray = input.toCharArray();
            for (int i=0; i<input.length(); i++){
                if (inputArray[i] != ' '){
                    index = i;
                    break;
                }
            }
            return input.substring(index);
        }else {
            return "";
        }
    }
}
