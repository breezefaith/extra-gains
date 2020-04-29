package competition.management.system.entity;

public class Grade extends AbstractEntity{
    protected Team team;
    protected Double pgrade;
    protected Double lgrade;
    protected HosterAdmin hosterAdmin;

    public Grade() {
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Double getPgrade() {
        return pgrade;
    }

    public void setPgrade(Double pgrade) {
        this.pgrade = pgrade;
    }

    public Double getLgrade() {
        return lgrade;
    }

    public void setLgrade(Double lgrade) {
        this.lgrade = lgrade;
    }

    public HosterAdmin getHosterAdmin() {
        return hosterAdmin;
    }

    public void setHosterAdmin(HosterAdmin hosterAdmin) {
        this.hosterAdmin = hosterAdmin;
    }
}
