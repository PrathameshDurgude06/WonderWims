import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faFacebook, faTwitter, faYoutube, faInstagram } from "@fortawesome/free-brands-svg-icons";


function Footer() {
  return (
    <div className="footer-content">
      <div className="footer-column">
        <div className="footer-column-header">
          <h3>Get connected</h3>
        </div>
        <div className="social-links">
          <FontAwesomeIcon icon={faFacebook} className="footer-icon" />
          <FontAwesomeIcon icon={faTwitter} className="footer-icon" />
          <FontAwesomeIcon icon={faYoutube} className="footer-icon" />
          <FontAwesomeIcon icon={faInstagram} className="footer-icon" />
        </div>
      </div>

      <div className="footer-column">
        <div className="footer-column-header">
          <h3>Project</h3>
        </div>
        <p className="footer-text">Home</p>
        <p className="footer-text">About</p>
        <p className="footer-text">Contact</p>
      </div>

      <div className="footer-column">
        <div className="footer-column-header">
          <h3>Community</h3>
        </div>
        <p className="footer-text">GitHub</p>
        <p className="footer-text">Issues</p>
        <p className="footer-text">Project</p>
      </div>

      <div className="footer-column">
        <div className="footer-column-header">
          <h3>Help</h3>
        </div>
        <p className="footer-text">Support</p>
        <p className="footer-text">Troubleshooting</p>
        <p className="footer-text">Contact</p>
      </div>

      <div className="footer-column">
        <div className="footer-column-header">
          <h3>Others</h3>
        </div>
        <p className="footer-text">Terms of Services</p>
        <p className="footer-text">Privacy Policy</p>
        <p className="footer-text">License</p>
      </div>
      
      
    </div>
  );
}

export default Footer;
