import React, { useState, useEffect } from "react";



export default function PotilasNakyma({ potilas, onClose }) {
    const [tab, setTab] = useState("tiedot");


    const [data, setData] = useState({
        ...potilas,
        id: potilas.id ?? potilas.Id,
        laakkeet: [],
        diagnoosit: potilas.diagnoosit ?? [],
        jatkohoito: Array.isArray(potilas.jatkohoito)
            ? potilas.jatkohoito
            : potilas.jatkohoito
                ? [potilas.jatkohoito]
                : [],
        hoitoohje: Array.isArray(potilas.hoitoohje)
            ? potilas.hoitoohje
            : potilas.hoitoohje
                ? [potilas.hoitoohje]
                : []
    });

    useEffect(() => {
        if (!data.id) return;

        if (tab === "laakkeet") haeLaakkeet();
        if (tab === "diagnoosit") haeDiagnoosit();
        if (tab === "jatkohoito") haeJatkohoito();
        if (tab === "hoitoohje") haeHoitoOhjeet();

    }, [tab, data.id]);





    const [input, setInput] = useState("");

    async function handleAdd() {
        if (!input.trim()) return;
        try {
            if (tab === "laakkeet") {
                const res = await fetch(
                    `http://localhost:8080/api/laakelista/lisaa/${data.id}`,
                    {
                        method: "POST",
                        headers: {"Content-Type": "application/json"},
                        body: JSON.stringify({nimi: input})
                    }
                );

                if (!res.ok) {
                    throw new Error("Lääkkeen lisäys epäonnistui");
                }

                const uusiLaake = await res.json();

                setData(prev => ({
                    ...prev,
                    laakkeet: [...prev.laakkeet, uusiLaake]
                }));
            }

            if (tab === "diagnoosit") {
                const res = await fetch(
                    `http://localhost:8080/api/diagnoosi/lisaa/${data.id}`,
                    {
                        method: "POST",
                        headers: { "Content-Type": "application/json" },
                        body: JSON.stringify({ diagnoosinimi: input })
                    }
                );

                if (!res.ok) {
                    throw new Error("Diagnoosin lisäys epäonnistui");
                }

                const uusiDiagnoosi = await res.json();

                const normalisoitu = {
                    ...uusiDiagnoosi,
                    id: uusiDiagnoosi.id ?? uusiDiagnoosi.Id
                };

                setData(prev => ({
                    ...prev,
                    diagnoosit: [...prev.diagnoosit, normalisoitu]
                }));
            }

            if (tab === "jatkohoito") {
                const res = await fetch(
                    `http://localhost:8080/api/jatkohoito/lisaa/${data.id}`,
                    {
                        method: "POST",
                        headers: { "Content-Type": "application/json" },
                        body: JSON.stringify({ jatkohoito_ohje: input })
                    }
                );

                const uusi = await res.json();

                setData(prev => ({
                    ...prev,
                    jatkohoito: [...prev.jatkohoito, uusi]
                }));
            }

            if (tab === "hoitoohje") {
                const res = await fetch(
                    `http://localhost:8080/api/hoitoohje/lisaa/${data.id}`,
                    {
                        method: "POST",
                        headers: { "Content-Type": "application/json" },
                        body: JSON.stringify({ ohje: input })
                    }
                );

                const uusi = await res.json();

                setData(prev => ({
                    ...prev,
                    hoitoohje: [...prev.hoitoohje, uusi]
                }));
            }
            setInput("");

        } catch (err) {
            console.error(err);
            alert("Virhe lisättäessä tietoa");
        }
    }



    async function handleDelete(type, id) {
        if (type === "laakkeet") {
            await fetch(
                `http://localhost:8080/api/laakelista/poista/${id}`,
                { method: "DELETE" }
            );

            setData(prev => ({
                ...prev,
                laakkeet: prev.laakkeet.filter(l => l.id !== id)
            }));
        }

        if (type === "diagnoosit") {
            await fetch(
                `http://localhost:8080/api/diagnoosi/poista/${id}`,
                { method: "DELETE" }
            );

            setData(prev => ({
                ...prev,
                diagnoosit: prev.diagnoosit.filter(d => (d.id ?? d.Id) !== id)
            }));
        }
        if (type === "jatkohoito") {
            await fetch(
                `http://localhost:8080/api/jatkohoito/poista/${id}`,
                { method: "DELETE" }
            );

            setData(prev => ({
                ...prev,
                jatkohoito: prev.jatkohoito.filter(j => j.id !== id)
            }));
        }

        if (type === "hoitoohje") {
            await fetch(
                `http://localhost:8080/api/hoitoohje/poista/${id}`,
                { method: "DELETE" }
            );

            setData(prev => ({
                ...prev,
                hoitoohje: prev.hoitoohje.filter(h => h.id !== id)
            }));
        }



    }

    async function haeLaakkeet() {
        try {
            const res = await fetch(
                `http://localhost:8080/api/laakelista/kaikki/${data.id}`
            );

            if (!res.ok) {
                throw new Error("Lääkkeiden haku epäonnistui");
            }

            const laakkeet = await res.json();

            const normalisoidut = laakkeet.map(l => ({
                ...l,
                id: l.id ?? l.Id
            }));

            setData(prev => ({
                ...prev,
                laakkeet: normalisoidut
            }));

        } catch (err) {
            console.error(err);
            alert("Virhe haettaessa lääkkeitä");
        }
    }

    async function haeDiagnoosit() {
        try {
            const res = await fetch(
                `http://localhost:8080/api/diagnoosi/kaikki/${data.id}`
            );

            if (!res.ok) {
                throw new Error("Diagnoosien haku epäonnistui");
            }

            const diagnoosit = await res.json();

            const normalisoidut = diagnoosit.map(d => ({
                ...d,
                id: d.id ?? d.Id
            }));

            setData(prev => ({
                ...prev,
                diagnoosit: normalisoidut
            }));

        } catch (err) {
            console.error(err);
            alert("Virhe haettaessa diagnooseja");
        }
    }

    async function haeJatkohoito() {
        try {
            const res = await fetch(
                `http://localhost:8080/api/jatkohoito/hae/jatkohoito/${data.id}`
            );

            if (!res.ok) {
                throw new Error("Jatkohoidon haku epäonnistui");
            }

            const lista = await res.json();

            const normalisoidut = lista.map(j => ({
                ...j,
                id: j.id ?? j.Id ?? j.jatkohoitoId
            }));

            setData(prev => ({
                ...prev,
                jatkohoito: normalisoidut
            }));

        } catch (err) {
            console.error(err);
            alert("Virhe haettaessa jatkohoitoa");
        }
    }

    async function haeHoitoOhjeet() {
        try {
            const res = await fetch(
                `http://localhost:8080/api/hoitoohje/hae/hoitoohje/${data.id}`
            );

            if (!res.ok) {
                throw new Error("Hoito-ohjeiden haku epäonnistui");
            }

            const lista = await res.json();

            const normalisoidut = lista.map(h => ({
                ...h,
                id: h.id ?? h.Id ?? h.hoitoohjeId
            }));

            setData(prev => ({
                ...prev,
                hoitoohje: normalisoidut
            }));

        } catch (err) {
            console.error(err);
            alert("Virhe haettaessa hoito-ohjeita");
        }
    }





    function getAddButtonLabel() {
        switch (tab) {
            case "laakkeet": return "Lisää lääke";
            case "diagnoosit": return "Lisää diagnoosi";
            case "jatkohoito": return "Lisää suunnitelma";
            case "hoitoohje": return "Lisää hoito-ohje";
            default: return null;
        }
    }

    return (
        <div style={styles.overlay}>
            <div style={styles.modal}>

                <h2 style={{ marginBottom: "10px" }}>
                    {data.etunimi} {data.sukunimi}
                </h2>

                {/* TABIT */}
                <div style={styles.tabs}>
                    <button style={tab === "tiedot" ? styles.activeTab : styles.tab} onClick={() => setTab("tiedot")}>Tiedot</button>
                    <button style={tab === "laakkeet" ? styles.activeTab : styles.tab} onClick={() => setTab("laakkeet")}>Lääkkeet</button>
                    <button style={tab === "diagnoosit" ? styles.activeTab : styles.tab} onClick={() => setTab("diagnoosit")}>Diagnoosit</button>
                    <button style={tab === "jatkohoito" ? styles.activeTab : styles.tab} onClick={() => setTab("jatkohoito")}>Jatkohoito</button>
                    <button style={tab === "hoitoohje" ? styles.activeTab : styles.tab} onClick={() => setTab("hoitoohje")}>Hoito-ohje</button>
                </div>

                {/* SISÄLTÖ (scrollattava) */}
                <div style={styles.contentScroll}>

                    {tab === "tiedot" && (
                        <div>
                            <p><strong>Ikä:</strong> {data.ika}</p>
                            <p><strong>Triage:</strong> {data.luokitus}</p>
                        </div>
                    )}

                    {tab === "laakkeet" && (
                        <div>
                            <h3>Lääkkeet</h3>

                            {data.laakkeet.length === 0 && <p>Ei lääkkeitä.</p>}

                            <ul style={styles.list}>
                                {data.laakkeet
                                    .filter(l => l && typeof l === "object" && l.id)
                                    .map(l => (
                                        <li key={l.id} style={styles.listItem}>
                                            {l.nimi}
                                            <button
                                                style={styles.deleteBtn}
                                                onClick={() => handleDelete("laakkeet", l.id)}
                                            >
                                                X
                                            </button>
                                        </li>
                                    ))}
                            </ul>
                        </div>
                    )}

                    {tab === "diagnoosit" && (
                        <div>
                            <h3>Diagnoosit</h3>

                            {data.diagnoosit.length === 0 && <p>Ei diagnooseja.</p>}

                            <ul style={styles.list}>
                                {data.diagnoosit
                                    .filter(d => d && (d.id ?? d.Id))
                                    .map(d => (
                                        <li
                                            key={d.id ?? d.Id}
                                            style={styles.listItem}
                                        >
                                            {d.diagnoosinimi}
                                            <button
                                                style={styles.deleteBtn}
                                                onClick={() =>
                                                    handleDelete("diagnoosit", d.id ?? d.Id)
                                                }
                                            >
                                                X
                                            </button>
                                        </li>
                                    ))}
                            </ul>
                        </div>
                    )}


                    {tab === "jatkohoito" && (
                        <div>
                            <h3>Jatkohoitosuunnitelma</h3>

                            {data.jatkohoito.length === 0 && <p>Ei suunnitelmaa.</p>}

                            <ul style={styles.list}>
                                {data.jatkohoito
                                    .filter(j => j && j.id)
                                    .map(j => (
                                        <li key={j.id} style={styles.listItem}>
                                            {j.jatkohoito_ohje}
                                            <button
                                                style={styles.deleteBtn}
                                                onClick={() => handleDelete("jatkohoito", j.id)}
                                            >
                                                X
                                            </button>
                                        </li>
                                    ))}
                            </ul>
                        </div>
                    )}


                    {tab === "hoitoohje" && (
                        <div>
                            <h3>Hoito-ohjeet</h3>

                            {data.hoitoohje.length === 0 && <p>Ei hoito-ohjeita.</p>}

                            <ul style={styles.list}>
                                {data.hoitoohje
                                    .filter(h => h && h.id)
                                    .map(h => (
                                        <li key={h.id} style={styles.listItem}>
                                            {h.ohje}
                                            <button
                                                style={styles.deleteBtn}
                                                onClick={() => handleDelete("hoitoohje", h.id)}
                                            >
                                                X
                                            </button>
                                        </li>
                                    ))}
                            </ul>
                        </div>
                    )}


                </div>

                {/* LISÄYSKENTTÄ */}
                {getAddButtonLabel() && (
                    <div style={styles.addRow}>
                        <input
                            value={input}
                            onChange={e => setInput(e.target.value)}
                            placeholder="Kirjoita uusi..."
                            style={styles.input}
                        />
                        <button style={styles.addBtn} onClick={handleAdd}>
                            {getAddButtonLabel()}
                        </button>
                    </div>
                )}

                <button style={styles.closeBtn} onClick={onClose}>
                    Sulje
                </button>

            </div>
        </div>
    );
}


const styles = {
    overlay: {
        position: "fixed",
        top: 0, left: 0,
        width: "100vw",
        height: "100vh",
        backgroundColor: "rgba(0,0,0,0.4)",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        zIndex: 9999,
        color: "black"
    },
    modal: {
        background: "linear-gradient(135deg, lightslategrey, white, lightslategrey)",
        width: "500px",
        padding: "20px",
        borderRadius: "12px",
        display: "flex",
        flexDirection: "column",
        gap: "15px",
        color: "black"
    },
    tabs: {
        display: "flex",
        gap: "5px",
        borderBottom: "1px solid #ccc",
        paddingBottom: "8px"
    },
    tab: {
        padding: "8px 10px",
        cursor: "pointer",
        background: "#eee",
        borderRadius: "6px",
        border: "none",
        color: "black"
    },
    activeTab: {
        padding: "8px 10px",
        cursor: "pointer",
        background: "#007bff",
        color: "white",
        borderRadius: "6px",
        border: "none"
    },
    contentScroll: {
        minHeight: "180px",
        maxHeight: "180px",
        overflowY: "auto",
        paddingRight: "10px"
    },
    list: {
        padding: 0,
        listStyle: "none"
    },
    listItem: {
        display: "flex",
        justifyContent: "space-between",
        padding: "6px 0",
        borderBottom: "1px solid #ddd"
    },
    deleteBtn: {
        background: "#dc3545",
        color: "white",
        border: "none",
        borderRadius: "4px",
        cursor: "pointer",
        padding: "2px 6px"
    },
    addRow: {
        display: "flex",
        gap: "10px",
        marginBottom: "10px"
    },
    input: {
        flex: 1,
        padding: "8px",
        borderRadius: "6px",
        border: "1px solid #ccc"
    },
    addBtn: {
        padding: "8px 12px",
        background: "#28a745",
        color: "white",
        border: "none",
        borderRadius: "6px",
        cursor: "pointer",
        whiteSpace: "nowrap"
    },
    closeBtn: {
        padding: "10px",
        background: "#dc3545",
        color: "white",
        border: "none",
        borderRadius: "6px",
        cursor: "pointer"
    }
};
