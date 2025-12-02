import { useState } from 'react'
import './App.css'
import Potilaslista from "./Potilaslista.jsx";
import KuormitusData from "./KuormitusData.jsx";
import UusiPotilas from "./UusiPotilas.jsx";
function Potilaskartta() {
    const [showModal, setShowModal] = useState(false);

    const [odottava,setOdottava] = useState([
        {id: 1, etunimi: "Maija", sukunimi: "Mehiläinen", ika: 44 },
        {id: 2, etunimi: "Pekka", sukunimi: "Pouta", ika: 87 }
    ]);

    const [ambulanssi, setAmbulanssi] = useState([
        {id: 3, etunimi: "Jouni", sukunimi: "Jokelainen", ika: 55 },
        {id: 4, etunimi: "Senja", sukunimi: "Seijalainen", ika: 87 }
    ]);


    const [paikat, setPaikat] = useState({
        1: null,
        2: null,
        3: null,
        4: null,
        5: null,
        6: null,
        7: null,
        8: null,
        9: null
    });

    // 🔥 TÄMÄ ON KOKO SOVELLUKSEN YDIN
    function handleTuoPotilas({ potilas, luokitus, paikka }) {
        if (!potilas) return;

        const uusi = { ...potilas, luokitus };

        setPaikat(prev => ({
            ...prev,
            [paikka]: uusi
        }));

        setOdottava(prev => prev.filter(p => p.id !== potilas.id));
        setAmbulanssi(prev => prev.filter(p => p.id !== potilas.id));

        setShowModal(false);
    }


  return (
      <>
      <h2 className= "potilas-kartta">Potilaskartta</h2>
          <div className="odottava_laatikko">
              <h3 className="odotusaula_teksti">Odotusaula</h3>
              <div className="odottavat_lista" >
                  <Potilaslista rows={odottava} />

              </div>
          </div>
          <div className="ambulanssi_laatikko">
              <h3 className="ambulanssipotilaat_teksti">Ambulanssi potilaat</h3>
              <div className="ambulanssi_lista" >
                  <Potilaslista rows={ambulanssi} />


              </div>
          </div>


      <div className= "paivystys_laatikko">
          <div className="kaytava1"></div>
          <div className="kaytava2"></div>
          <div className="kaytava3"></div>
          <div className="kaytava4"></div>


          <h3 className= "hallintapaneeliteksti">Hallintapaneeli</h3>
          <div
              className="hallinta">
              <button className="luo_nappi" onClick={() => setShowModal(true)}>tuo potilas</button>
              <button className="poista_nappi">kotiuta potilas</button>
              {showModal && (
                  <UusiPotilas
                      odottava={odottava}
                      ambulanssi={ambulanssi}
                      paikat={paikat}
                      onTuoPotilas={handleTuoPotilas}
                      onClose={() => setShowModal(false)}
                  />

              )}
              <h6 className="paivytyksentila_teksti">Päivystyksen kuormitus</h6>
              <div style={{ width: "200px", margin: "0 auto" }}>
                  <div style={{
                      width: "170px",
                      position: "relative",
                      left: "-8px",   // vasemmalle
                      top: "230px",     // alaspäin
                  }}>
                      <KuormitusData />
                  </div>
              </div>
          </div>
          <div className= "potilaspaikka1">
              <h6 className= "numero1">Paikka 1</h6>
          </div>
          <div className= "potilaspaikka2">
              <h6 className= "numero2">Paikka 2</h6>
          </div>
          <div className= "potilaspaikka3">
              <h6 className= "numero3">Paikka 3</h6>
          </div>
          <div className= "potilaspaikka4">
              <h6 className= "numero4">Paikka 4</h6>
          </div>
          <div className= "potilaspaikka5">
              <h6 className= "numero5">Paikka 5</h6>
          </div>
          <div className= "potilaspaikka6">
              <h6 className= "numero6">Paikka 6</h6>
          </div>
          <div className= "potilaspaikka7">
              <h6 className= "numero7">Paikka 7</h6>
          </div>
          <div className= "potilaspaikka8">
              <h6 className= "numero8">Paikka 8</h6>
          </div>
          <div className= "potilaspaikka9">
              <h6 className= "numero9">Paikka 9</h6>
          </div>
      </div>
      </>
  );
}

export default Potilaskartta
