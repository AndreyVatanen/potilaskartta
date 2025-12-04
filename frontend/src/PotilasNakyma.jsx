import React, { useState } from "react";

export default function PotilasNakyma({ potilas, onClose }) {
    const [tab, setTab] = useState("tiedot");


    const [data, setData] = useState({
        ...potilas,
        laakkeet: potilas.laakkeet ?? [],
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

    const [input, setInput] = useState("");

    function handleAdd() {
        if (!input.trim()) return;

        if (tab === "laakkeet") {
            setData(prev => ({
                ...prev,
                laakkeet: [...prev.laakkeet, input]
            }));
        }

        if (tab === "diagnoosit") {
            setData(prev => ({
                ...prev,
                diagnoosit: [...prev.diagnoosit, input]
            }));
        }

        if (tab === "jatkohoito") {
            setData(prev => ({
                ...prev,
                jatkohoito: [...prev.jatkohoito, input]
            }));
        }

        if (tab === "hoitoohje") {
            setData(prev => ({
                ...prev,
                hoitoohje: [...prev.hoitoohje, input]
            }));
        }

        setInput("");
    }

    function handleDelete(type, index) {
        setData(prev => ({
            ...prev,
            [type]: prev[type].filter((_, i) => i !== index)
        }));
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
                                {data.laakkeet.map((l, i) => (
                                    <li key={i} style={styles.listItem}>
                                        {l}
                                        <button style={styles.deleteBtn} onClick={() => handleDelete("laakkeet", i)}>X</button>
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
                                {data.diagnoosit.map((d, i) => (
                                    <li key={i} style={styles.listItem}>
                                        {d}
                                        <button style={styles.deleteBtn} onClick={() => handleDelete("diagnoosit", i)}>X</button>
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
                                {data.jatkohoito.map((j, i) => (
                                    <li key={i} style={styles.listItem}>
                                        {j}
                                        <button style={styles.deleteBtn} onClick={() => handleDelete("jatkohoito", i)}>X</button>
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
                                {data.hoitoohje.map((h, i) => (
                                    <li key={i} style={styles.listItem}>
                                        {h}
                                        <button style={styles.deleteBtn} onClick={() => handleDelete("hoitoohje", i)}>X</button>
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
        background: "white",
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
