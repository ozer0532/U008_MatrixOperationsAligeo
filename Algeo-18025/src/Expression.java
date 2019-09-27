public class Expression {
    private double[] exp = new double [Nilai.varList.length() + 1];

    private static int accVarIndex = 1;

    public String GenerateNewVar () {
        String var = Nilai.GetVarName(accVarIndex);
        exp[accVarIndex] = 1;
        accVarIndex++;

        return var;
    }

    public double GetConst () {
        return exp[0];
    }

    public Nilai GetVariable (String var) {
        return GetVariable(Nilai.GetVarIndex(var));
    }

    public Nilai GetVariable (int var) {
        return new Nilai(exp[var], var);
    }

    public void SetConst (double val) {
        exp[0] = val;
    }

    public void SetVariable (String var, double val) {
        SetVariable(Nilai.GetVarIndex(var), val);
    }

    public void SetVariable (int var, double val) {
        exp[var] = val;
    }

    public String ToString () {
        String out = "";
        for (int i = 1; i < exp.length; i++) {
            if (exp[i] != 0) {
                if (out == "") {
                    if (exp[i] == 1) {
                        out = Nilai.GetVarName(i);
                    } else if (exp[i] == -1) {
                        out = "-" + Nilai.GetVarName(i);
                    } else {
                        out = String.format("%.2f", exp[i]) + Nilai.GetVarName(i);
                    }
                } else {
                    if (exp[i] > 0) {
                        if (exp[i] != 1) {
                            out += " + " + String.format("%.2f", exp[i]) + Nilai.GetVarName(i);
                        } else {
                            out += " + " + Nilai.GetVarName(i);
                        }
                    } else {
                        if (exp[i] != -1) {
                            out += " - " + String.format("%.2f", exp[i] * -1) + Nilai.GetVarName(i);
                        } else {
                            out += " - " + Nilai.GetVarName(i);
                        }
                    }
                }
            }
        }
        if (exp[0] != 0) {
            if (out == "") {
                out = String.format("%.2f", exp[0]);
            } else {
                if (exp[0] > 0) {
                    out += " + " + String.format("%.2f", exp[0]);
                } else {
                    out += " - " + String.format("%.2f", exp[0] * -1);
                }
            }
        } else {
            if (out == "") {
                out = "0";
            }
        }
        return out;
    }

    public boolean IsEmpty () {
        for (int i = 0; i < exp.length; i++) {
            if (exp[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void ResetVars() {
        accVarIndex = 1;
    }
}