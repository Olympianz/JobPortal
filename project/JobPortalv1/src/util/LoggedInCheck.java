package util;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoggedInCheck implements PhaseListener {
	private static final long serialVersionUID = 6543087035084905864L;

	@Override
	public void afterPhase(PhaseEvent event) {
	}

	@Override
	public void beforePhase(PhaseEvent event) {

		ExternalContext ec = event.getFacesContext().getExternalContext();

		// Check logged-in by calling SessionCtl object
		if (!SessionCtl.checkLogIn((HttpServletRequest) ec.getRequest(),
				(HttpServletResponse) ec.getResponse())) {
			
			try {
				ec.redirect("index.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;// PhaseId.RESTORE_VIEW;
	}

}
