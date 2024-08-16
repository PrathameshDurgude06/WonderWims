/* eslint-disable react/prop-types */

function CardProfile() {
    return (
        <div className="profileclass">
            <div >
                <h1 className="about" style={{ fontSize: 50 }}>Contact Us</h1>
            </div>
            <div style={{justifyContent:"center",alignItems:"center"}} class="row row-cols-3 g-3">
                <div class="column">
                    <div className="card">
                        { <img src="Prathamesh.jpeg" className="ii" height={250} /> }

                        <div className="profile-details">
                            <h2 className="tour-price">Prathamesh Durgude</h2>
                            <h2 className="tour-name">IACSD Pune</h2>


                        </div>
                        <p>Mobile no: 8605457388</p>
                        <p>Email ID: pdurgude63@gmail.com </p>
                        <p>Address: Nashik, Maharashtra</p>
                        

                    </div>
                </div>

                <div class="column">
                    <div className="card">
                        { <img src="Aditya.jpg" className="ii" height={250} /> }

                        <div className="profile-details">
                            <h2 className="tour-price">Aditya Barahate</h2>
                            <h2 className="tour-name">IACSD Pune</h2>


                        </div>
                        <p>Mobile no: 9545353528</p>
                        <p>Email ID: adityabarahate5@gmail.com </p>
                        <p>Address: Nashik, Maharashtra</p>
                       

                    </div>
                </div>
            </div>
        </div>
    );
}

export default CardProfile;
