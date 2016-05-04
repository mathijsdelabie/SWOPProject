package model.projects.health;

import java.util.List;

public class HealthCalculator1 extends HealthCalculator {

	@Override
	protected boolean isHealthy(double bugImpact, List<HealthIndicator> healthIndicatorsOfSubsystems) {
		return isHealthIndicator(bugImpact, healthIndicatorsOfSubsystems, HealthIndicator.HEALTHY, 50, HealthIndicator.HEALTHY);
	}

	@Override
	protected boolean isSatisfactory(double bugImpact, List<HealthIndicator> healthIndicatorsOfSubsystems) {
		return isHealthIndicator(bugImpact, healthIndicatorsOfSubsystems, HealthIndicator.SATISFACTORY, 100, HealthIndicator.SATISFACTORY);
	}

	@Override
	protected boolean isStable(double bugImpact, List<HealthIndicator> healthIndicatorsOfSubsystems) {
		return isHealthIndicator(bugImpact, healthIndicatorsOfSubsystems, HealthIndicator.STABLE, 500, HealthIndicator.STABLE);
	}

	@Override
	protected boolean isSerious(double bugImpact, List<HealthIndicator> healthIndicatorsOfSubsystems) {
		return isHealthIndicator(bugImpact, healthIndicatorsOfSubsystems, HealthIndicator.SERIOUS, 1000, HealthIndicator.SERIOUS);
	}

	

}
