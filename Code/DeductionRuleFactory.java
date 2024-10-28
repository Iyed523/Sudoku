public class DeductionRuleFactory {
    public static DeductionRule createRule(String ruleType, int[][] grid) {
        switch (ruleType) {
            case "DR1":
                return new DR1(grid); 
            case "DR2":
                return new DR2(grid); 
            case "DR3":
                return new DR3(grid);
            default:
                throw new IllegalArgumentException("Règle non reconnue: " + ruleType);
        }
    }
}
