public class Nilai {
    public static final String varList = "abcdefghijklmnopqrstuvwxyz";

    public double val;
    public int var;
    public String varName;

    public Nilai (double val, int var) {
        this.val = val;
        this.var = var;
        this.varName = GetVarName(var);
    }

    public static String GetVarName (int var) {
        if (var == 0) {
            return "";
        } else {
            return varList.substring(var - 1, var);
        }
    }

    public static int GetVarIndex (String var) {
        return varList.indexOf(var) + 1;
    }
}