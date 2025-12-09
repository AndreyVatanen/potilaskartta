import './App.css'
import { useState, useEffect } from "react";
import Potilaslista from "./Potilaslista.jsx";
import KuormitusData from "./KuormitusData.jsx";
import UusiPotilas from "./UusiPotilas.jsx";
import KotiutaPotilas from "./KotiutaPotilas.jsx";
import PotilasNakyma from "./PotilasNakyma.jsx";


function Potilaskartta() {
    const [showModal, setShowModal] = useState(false);
    const [showKotiuta, setShowKotiuta] = useState(false);
    const [naytaPotilas, setNaytaPotilas] = useState(null);
    const [odottava, setOdottava] = useState([]);
    const [ambulanssi, setAmbulanssi] = useState([]);


    useEffect(() => {
        async function haeOdotusaula() {
            try {
                const response = await fetch("http://localhost:8080/api/odotusaula/kaikki/1");
                if (!response.ok) {
                    throw new Error("Virhe " + response.status);
                }
                const data = await response.json();


                const normalized = data.map(p => ({
                    ...p,
                    id: p.id ?? p.Id
                }));

                setOdottava(normalized);


            } catch (err) {
                console.error("Virhe haettaessa:", err);
            }
        }

        haeOdotusaula();
    }, []);


    useEffect(() => {
        async function haeAmbulanssi() {
            try {
                const response = await fetch("http://localhost:8080/api/ambulanssi/kaikki/1");
                if (!response.ok) {
                    throw new Error("Virhe " + response.status);
                }
                const data = await response.json();

                const normalized = data.map(p => ({
                    ...p,
                    id: p.id ?? p.Id
                }));

                setAmbulanssi(normalized);


            } catch (err) {
                console.error("Virhe haettaessa:", err);
            }
        }

        haeAmbulanssi();
    }, []);


    //-----------

    useEffect(() => {
        async function haePaikat() {
            const res = await fetch("http://localhost:8080/api/paikka/osastopotilaat/1");
            const data = await res.json();

            const paikatObj = {
                1: null, 2: null, 3: null, 4: null,
                5: null, 6: null, 7: null, 8: null, 9: null
            };

            data.forEach(p => {
                const nro = p.paikka?.paikkanumero;
                if (nro) {
                    paikatObj[nro] = {
                        ...p,
                        id: p.Id,
                        luokitus: p.kiireellisyys
                    };
                }
            });

            setPaikat(paikatObj);
        }

        haePaikat();
    }, []);






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

    function mapLuokitus(l) {
        if (l === "A") return "PUNAINEN";
        if (l === "B") return "KELTAINEN";
        return "VIHREA";
    }

    async function handleTuoPotilas({ potilas, luokitus, paikka }) {
        if (!potilas) return;

        try {

            await fetch(
                `http://localhost:8080/api/kiireellisyys/muokkaa/${potilas.id}`,
                {
                    method: "PUT",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(mapLuokitus(luokitus))
                }
            );


            await fetch(
                `http://localhost:8080/api/paikka/vie/${paikka}/${potilas.id}`,
                { method: "POST" }
            );

            const uusi = {
                ...potilas,
                luokitus: mapLuokitus(luokitus),
                paikka
            };

            setPaikat(prev => ({ ...prev, [paikka]: uusi }));
            setOdottava(prev => prev.filter(p => p.id !== potilas.id));
            setAmbulanssi(prev => prev.filter(p => p.id !== potilas.id));
            setShowModal(false);

        } catch (err) {
            console.error(err);
            alert("Virhe potilaan tuonnissa");
        }
    }







    async function handleKotiuta({ potilas, teksti }) {
        try {
            await fetch(
                `http://localhost:8080/api/potilas/poista/${potilas.id}`,
                { method: "DELETE" }
            );
        } catch (err) {
            alert("Virhe kotiutuksessa");
            return;
        }

        // poista frontend-tilasta
        setOdottava(prev => prev.filter(p => p.id !== potilas.id));
        setAmbulanssi(prev => prev.filter(p => p.id !== potilas.id));

        setPaikat(prev => {
            const copy = { ...prev };
            for (const key of Object.keys(copy)) {
                if (copy[key]?.id === potilas.id) {
                    copy[key] = null;
                }
            }
            return copy;
        });

        setShowKotiuta(false);
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
              <button className="poista_nappi" onClick={() => setShowKotiuta(true)}>kotiuta potilas</button>
              {showModal && (
                  <UusiPotilas
                      odottava={odottava}
                      ambulanssi={ambulanssi}
                      paikat={paikat}
                      onTuoPotilas={handleTuoPotilas}
                      onClose={() => setShowModal(false)}
                  />

              )}
              {showKotiuta && (
                  <KotiutaPotilas
                      odottava={odottava}
                      ambulanssi={ambulanssi}
                      paikat={paikat}
                      onKotiuta={handleKotiuta}
                      onClose={() => setShowKotiuta(false)}
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

              {paikat[1] && (
                  <div className="paikka-potilas">
                      <span className={`triage-indicator ${paikat[1].luokitus}`}></span>

                      <span className="potilas-teksti" style={{ fontSize: "14px", fontWeight: "500" }}>
            {paikat[1].etunimi} {paikat[1].sukunimi}
        </span>

                      <button
                          style={{
                              marginTop: "65px",
                              padding: "4px 8px",
                              fontSize: "12px",
                              borderRadius: "6px",
                              border: "none",
                              background: "#007bff",
                              color: "white",
                              cursor: "pointer",
                              //tähän parempi asetelma napin sijaintiin?

                          }}
                          onClick={() => setNaytaPotilas(paikat[1])}
                      >
                          potilastiedot
                      </button>
                  </div>
              )}


          </div>
          <div className= "potilaspaikka2">
              <h6 className= "numero2">Paikka 2</h6>

              {paikat[2] && (
                  <div className="paikka-potilas">
                      <span className={`triage-indicator ${paikat[2].luokitus}`}></span>

                      <span className="potilas-teksti" style={{ fontSize: "14px", fontWeight: "500" }}>
            {paikat[2].etunimi} {paikat[2].sukunimi}
        </span>

                      <button
                          style={{
                              marginTop: "65px",
                              padding: "4px 8px",
                              fontSize: "12px",
                              borderRadius: "6px",
                              border: "none",
                              background: "#007bff",
                              color: "white",
                              cursor: "pointer"
                          }}
                          onClick={() => setNaytaPotilas(paikat[2])}
                      >
                          potilastiedot
                      </button>
                  </div>
              )}

          </div>
          <div className= "potilaspaikka3">
              <h6 className= "numero3">Paikka 3</h6>

              {paikat[3] && (
                  <div className="paikka-potilas">
                      <span className={`triage-indicator ${paikat[3].luokitus}`}></span>

                      <span className="potilas-teksti" style={{ fontSize: "14px", fontWeight: "500" }}>
            {paikat[3].etunimi} {paikat[3].sukunimi}
        </span>

                      <button
                          style={{
                              marginTop: "65px",
                              padding: "4px 8px",
                              fontSize: "12px",
                              borderRadius: "6px",
                              border: "none",
                              background: "#007bff",
                              color: "white",
                              cursor: "pointer"
                          }}
                          onClick={() => setNaytaPotilas(paikat[3])}
                      >
                          potilastiedot
                      </button>
                  </div>
              )}


          </div>
          <div className= "potilaspaikka4">
              <h6 className= "numero4">Paikka 4</h6>

              {paikat[4] && (
                  <div className="paikka-potilas">
                      <span className={`triage-indicator ${paikat[4].luokitus}`}></span>

                      <span className="potilas-teksti" style={{ fontSize: "14px", fontWeight: "500" }}>
            {paikat[4].etunimi} {paikat[4].sukunimi}
        </span>

                      <button
                          style={{
                              marginTop: "65px",
                              padding: "4px 8px",
                              fontSize: "12px",
                              borderRadius: "6px",
                              border: "none",
                              background: "#007bff",
                              color: "white",
                              cursor: "pointer"
                          }}
                          onClick={() => setNaytaPotilas(paikat[4])}
                      >
                          potilastiedot
                      </button>
                  </div>
              )}


          </div>
          <div className= "potilaspaikka5">
              <h6 className= "numero5">Paikka 5</h6>

              {paikat[5] && (
                  <div className="paikka-potilas">
                      <span className={`triage-indicator ${paikat[5].luokitus}`}></span>

                      <span className="potilas-teksti" style={{ fontSize: "14px", fontWeight: "500" }}>
            {paikat[5].etunimi} {paikat[5].sukunimi}
        </span>

                      <button
                          style={{
                              marginTop: "65px",
                              padding: "4px 8px",
                              fontSize: "12px",
                              borderRadius: "6px",
                              border: "none",
                              background: "#007bff",
                              color: "white",
                              cursor: "pointer"
                          }}
                          onClick={() => setNaytaPotilas(paikat[5])}
                      >
                          potilastiedot
                      </button>
                  </div>
              )}


          </div>
          <div className= "potilaspaikka6">
              <h6 className= "numero6">Paikka 6</h6>

              {paikat[6] && (
                  <div className="paikka-potilas">
                      <span className={`triage-indicator ${paikat[6].luokitus}`}></span>

                      <span className="potilas-teksti" style={{ fontSize: "14px", fontWeight: "500" }}>
            {paikat[6].etunimi} {paikat[6].sukunimi}
        </span>

                      <button
                          style={{
                              marginTop: "65px",
                              padding: "4px 8px",
                              fontSize: "12px",
                              borderRadius: "6px",
                              border: "none",
                              background: "#007bff",
                              color: "white",
                              cursor: "pointer"
                          }}
                          onClick={() => setNaytaPotilas(paikat[6])}
                      >
                          potilastiedot
                      </button>
                  </div>
              )}



          </div>
          <div className= "potilaspaikka7">
              <h6 className= "numero7">Paikka 7</h6>

              {paikat[7] && (
                  <div className="paikka-potilas">
                      <span className={`triage-indicator ${paikat[7].luokitus}`}></span>

                      <span className="potilas-teksti" style={{ fontSize: "14px", fontWeight: "500" }}>
            {paikat[7].etunimi} {paikat[7].sukunimi}
        </span>

                      <button
                          style={{
                              marginTop: "65px",
                              padding: "4px 8px",
                              fontSize: "12px",
                              borderRadius: "6px",
                              border: "none",
                              background: "#007bff",
                              color: "white",
                              cursor: "pointer"
                          }}
                          onClick={() => setNaytaPotilas(paikat[7])}
                      >
                          potilastiedot
                      </button>
                  </div>
              )}



          </div>
          <div className= "potilaspaikka8">
              <h6 className= "numero8">Paikka 8</h6>

              {paikat[8] && (
                  <div className="paikka-potilas">
                      <span className={`triage-indicator ${paikat[8].luokitus}`}></span>

                      <span className="potilas-teksti" style={{ fontSize: "14px", fontWeight: "500" }}>
            {paikat[8].etunimi} {paikat[8].sukunimi}
        </span>

                      <button
                          style={{
                              marginTop: "65px",
                              padding: "4px 8px",
                              fontSize: "12px",
                              borderRadius: "6px",
                              border: "none",
                              background: "#007bff",
                              color: "white",
                              cursor: "pointer"
                          }}
                          onClick={() => setNaytaPotilas(paikat[8])}
                      >
                          potilastiedot
                      </button>
                  </div>
              )}



          </div>
          <div className= "potilaspaikka9">
              <h6 className= "numero9">Paikka 9</h6>


              {paikat[9] && (
                  <div className="paikka-potilas">
                      <span className={`triage-indicator ${paikat[9].luokitus}`}></span>

                      <span className="potilas-teksti" style={{ fontSize: "14px", fontWeight: "500" }}>
            {paikat[9].etunimi} {paikat[9].sukunimi}
        </span>

                      <button
                          style={{
                              marginTop: "65px",
                              padding: "4px 8px",
                              fontSize: "12px",
                              borderRadius: "6px",
                              border: "none",
                              background: "#007bff",
                              color: "white",
                              cursor: "pointer"
                          }}
                          onClick={() => setNaytaPotilas(paikat[9])}
                      >
                          potilastiedot
                      </button>
                  </div>
              )}

          </div>
      </div>
          {naytaPotilas && (
              <PotilasNakyma
                  potilas={naytaPotilas}
                  onClose={() => setNaytaPotilas(null)}
              />
          )}

      </>


  );
}

export default Potilaskartta
