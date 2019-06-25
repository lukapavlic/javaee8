package si.um.feri.javaee.knjiznica.demo.ejb;

import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import si.um.feri.javaee.knjiznica.vao.Knjigomat;

/**
 * Razred, ki demonstrira, kako poteka klicanje preko različnih vmesnikov
 *  - lokalno zrno: serializacija objektov se ne izvaja (čisti lokalni klici)
 *  - lokalen vmesnik: serializacija objektov se ne izvaja (čisti lokalni klici)
 *  - oddaljen zrno: serializacija objektov SE izvaja
 *
 */
@Local(DemoSerializableLocal.class)
@Remote(DemoSerializableRemote.class)
@Stateless
@LocalBean
public class DemoSerializable implements DemoSerializableLocal, DemoSerializableRemote {
	
	Logger log = Logger.getLogger(DemoSerializable.class.getSimpleName());

	@Resource
	SessionContext ctx;
	
	@Override
	public void dajMi(Knjigomat k) {
		log.info("Dobil sem "+k+" preko "+ctx.getInvokedBusinessInterface());
	}

}
