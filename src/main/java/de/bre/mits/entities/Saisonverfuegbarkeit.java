package de.bre.mits.entities;

import javax.persistence.Embeddable;

@Embeddable
public class Saisonverfuegbarkeit {

	private boolean januar;
	private boolean februar;
	private boolean maerz;
	private boolean april;
	private boolean mai;
	private boolean juni;
	private boolean juli;
	private boolean august;
	private boolean september;
	private boolean oktober;
	private boolean november;
	private boolean dezember;
	
	
	protected Saisonverfuegbarkeit(){
		
	}	
	
	public Saisonverfuegbarkeit(boolean januar,boolean februar,boolean maerz,boolean april,boolean mai,boolean juni,
			boolean juli,boolean august,boolean september,boolean oktober,boolean november,boolean dezember){
		
		
		this.januar=januar;
		this.februar=februar;
		this.maerz=maerz;
		this.april=april;
		this.mai=mai;
		this.juni=juni;
		this.juli=juli;
		this.august=august;
		this.september=september;
		this.oktober=oktober;
		this.november=november;
		this.dezember=dezember;
	}
	
	public boolean getJanuar(){
		return januar;
	}	
	public boolean getFebruar(){
		return februar;
	}
	public boolean getMaerz(){
		return maerz;
	}
	public boolean getApril(){
		return april;
	}
	public boolean getMai(){
		return mai;
	}
	public boolean getJuni(){
		return juni;
	}
	public boolean getJuli(){
		return juli;
	}
	public boolean getAugust(){
		return august;
	}
	public boolean getSeptember(){
		return september;
	}
	public boolean getOktober(){
		return oktober;
	}
	public boolean getNovember(){
		return november;
	}
	public boolean getDezember(){
		return dezember;
	}
}
