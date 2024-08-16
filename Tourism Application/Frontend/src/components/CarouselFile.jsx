import React from 'react';
import { Carousel } from 'react-bootstrap'; // Import Carousel from react-bootstrap

function CarouselFile() {
  return (
    <Carousel data-bs-theme="dark">
      <Carousel.Item>
        <img
          className="d-block w-100 "
          src="manali.jpg" // Adjust the path to your image file
          alt="First slide"
          height={600}          
        />
        <Carousel.Caption>
          <div style={{backgroundColor: '#ffffffb8'}}>
          <h5 color='black'>Manali Hill Stations </h5>
          <p color='black'>Manali is a high-altitude Himalayan resort town in India’s northern Himachal Pradesh state. It has a reputation as a backpacking center and honeymoon destination.</p>
          </div>
          
        </Carousel.Caption>
      </Carousel.Item>
      <Carousel.Item>
        <img
          className="d-block w-100"
          src="beach1.jpg" // Adjust the path to your image file
          alt="Second slide"
          height={600} 
        />
        <Carousel.Caption>
        <div style={{backgroundColor: '#ffffffb8'}}>
          <h5 color='black'>Agonda Beach,South Goa</h5>
          <p color='black'>Agonda, situated within the Canacona region of South Goa district, India, is renowned for its serene beach. This hidden gem offers a pristine getaway. The sand is clean, the beach is unspoiled, and the atmosphere is incredibly relaxing. </p>
          </div>
        </Carousel.Caption>
      </Carousel.Item>
      <Carousel.Item>
        <img
          className="d-block w-100"
          src="tajmahal.jpg" // Adjust the path to your image file
          alt="Third slide"
          height={600} 
        />
        <Carousel.Caption>
        <div style={{backgroundColor: '#ffffffb8'}}>
          <h5 color='black'>Taj Mahal, Agra</h5>
          <p color='black'>Taj Mahal, the architectural wonder known for being the epitome of beauty and grace, is located in Agra. It is famous as it is one of the world's seven wonders, and it is made of pure marble! The carvings on the wall of the Taj Mahal will leave you surprised as they are so intricate and beautiful.</p>
          </div>
        </Carousel.Caption>
      </Carousel.Item>
      <Carousel.Item>
        <img
          className="d-block w-100 "
          src="citypalace.jpg" // Adjust the path to your image file
          alt="First slide"
          height={600}          
        />
        <Carousel.Caption>
        <div style={{backgroundColor: '#ffffffb8'}}>
          <h5 color='black'>The City Palace, Jaipur</h5>
          <p color='black'>This magnificent City Palace of Jaipur stands proud before the slanting hills of Rajasthan. In the evening the building is suffused in a golden glow. During the day, tourists admire the intricacy of the Peacock Gates, and wander the buildings and gardens.</p>
          </div>
        </Carousel.Caption>
      </Carousel.Item>
      <Carousel.Item>
        <img
          className="d-block w-100 "
          src="kedaranth.jpg" // Adjust the path to your image file
          alt="First slide"
          height={600}          
        />
        <Carousel.Caption>
        <div style={{backgroundColor: '#ffffffb8'}}>
          <h5 color='black'>Kedarnath Temple, Uttarakhand, India.</h5>
          <p color='black'>Kedarnath Temple is a Hindu temple, one of the twelve jyotirlinga of Shiva. The temple is located on the Garhwal Himalayan range near the Mandakini river, in the state of Uttarakhand, India.</p>
          </div>
        </Carousel.Caption>
      </Carousel.Item>
      <Carousel.Item>
        <img
          className="d-block w-100 "
          src="Maysore.jpg" // Adjust the path to your image file
          alt="First slide"
          height={600}          
        />
        <Carousel.Caption>
        <div style={{backgroundColor: '#ffffffb8'}}>
          <h5 color='black'>Maysore Palace , Maysore</h5>
          <p color='black'>Mysore Palace, also known as Amba Vilas Palace, is a historical palace and a royal residence. It is located in Mysore, Karnataka, India. It used to be the official residence of the Wadiyar dynasty and the seat of the Kingdom of Mysore.  </p>
          </div>
        </Carousel.Caption>
      </Carousel.Item>
      <Carousel.Item>
        <img
          className="d-block w-100 "
          src="gateway.jpg" // Adjust the path to your image file
          alt="First slide"
          height={600}          
        />
        <Carousel.Caption>
        <div style={{backgroundColor: '#ffffffb8'}}>
          <h5 color='black'>Gateway Of India, Mumbai</h5>
          <p color='black'>Gateway of India is one of the most important landmarks in Mumbai and primarily a top attraction on every tourist’s list thanks to its grandeur and undying charm. The structure serves as a beautiful remembrance of the rich cultural heritage and it's colonial past.</p>
          </div>
        </Carousel.Caption>
      </Carousel.Item>
      
    </Carousel>
  );
}

export default CarouselFile;








// import Carousel from 'react-bootstrap/Carousel';

// function CarouselFile() {
//   return (
//     <Carousel data-bs-theme="dark">
//       <Carousel.Item>
//         <img
//           className="d-block w-100"
//           src="2.jpg"
//           alt="First slide"
//         />
//         <Carousel.Caption>
//           <h5>First slide label</h5>
//           <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
//         </Carousel.Caption>
//       </Carousel.Item>
//       <Carousel.Item>
//         <img
//           className="d-block w-100"
//           src="3.png"
//           alt="Second slide"
//         />
//         <Carousel.Caption>
//           <h5>Second slide label</h5>
//           <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
//         </Carousel.Caption>
//       </Carousel.Item>
//       <Carousel.Item>
//         <img
//           className="d-block w-100"
//           src="4.jpg"
//           alt="Third slide"
//         />
//         <Carousel.Caption>
//           <h5>Third slide label</h5>
//           <p>
//             Praesent commodo cursus magna, vel scelerisque nisl consectetur.
//           </p>
//         </Carousel.Caption>
//       </Carousel.Item>
//     </Carousel>
//   );
// }

// export default CarouselFile;