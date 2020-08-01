// Fill in the missing code
import React, { useEffect, useState } from 'react'
import { Container } from 'react-bootstrap';
import ReactDOM from 'react-dom';
import { Link } from 'react-router-dom';
import { toast } from 'react-toastify';
import Axios from 'axios';

const Index = function ({ user }) {
  const [tours, setTours] = useState([]);
  
  useEffect(() => {
    async function getData(){
      getTours();
    };

    getData();
  }, []); 

  async function getTours(){
    try {
      const resp = await Axios.get('/api/tours');
      if (resp.status === 200) {
        setTours(resp.data);
      } else {
        toast("There was an issue.", {
          type: toast.TYPE.ERROR
        });
      }
    } catch (err) {
      toast(err.response.data.message, {
        type: toast.TYPE.ERROR
      });
    }
  }

  const deleteTour = async function (tour) {
    try {
      const resp = await Axios.post('/api/tours/delete', tour);
      if (resp.status === 200) {
        getTours();
        toast('Removed tour successfully', {
          type: toast.TYPE.SUCCESS
        });
      } else {
        toast("There was an issue.", {
          type: toast.TYPE.ERROR
        });
      }
    } catch (err) {
      toast(err.response.data.message, {
        type: toast.TYPE.ERROR
      });
    }
  };

  return (
    <Container className="my-5">
      <header>
        <h1>Tours</h1>
      </header>

      <hr />

      <div className="content">
        {tours && tours.map((tour, i) => (
          <div key={i} className="card my-3">
            <div className="card-header">
              <h5 className="card-title">
                {tour.title}
              </h5>
            </div>

            <div className="card-body">
              <p className="card-text">
                A {tour.groupSize} {tour.groupSize > 1 ? 'people' : 'person'} group for the "{tour.tourType}" haunted tour on the date of {tour.date}.
              </p>
            </div>

            {user ? (
              <div className="card-footer">
                <Link to={{
                  pathname: "/tours/edit",
                  state: {
                    id: tour._id
                  }
                }}>
                  <i className="fa fa-edit"></i>
                </Link>

                <button type="button" onClick={() => deleteTour(tour)}>
                  <i className="fa fa-trash"></i>
                </button>
              </div>
            ) : null}
          </div>
        ))}
      </div>
    </Container>
  );

};

export default Index;