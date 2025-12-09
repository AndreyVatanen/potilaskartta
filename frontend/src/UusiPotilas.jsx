import { useState } from "react";

export default function UusiPotilas({
                                        odottava,
                                        ambulanssi,
                                        paikat,
                                        onTuoPotilas,
                                        onClose
                                    }) {

    const kaikkiPotilaat = [...odottava, ...ambulanssi];

    const [valittuId, setValittuId] = useState("");
    const [luokitus, setLuokitus] = useState("C");
    const [valittuPaikka, setValittuPaikka] = useState(1);

    // 🔥 UUSI: potilaan sijainnin selvitys dropdownia varten
    function haePotilaanSijainti(potilas) {
        if (odottava.some(p => p.id === potilas.id)) return "odotusaula";
        if (ambulanssi.some(p => p.id === potilas.id)) return "ambulanssi";
        return "";
    }

    function handleSave() {
        const potilas = kaikkiPotilaat.find(p => p.id === Number(valittuId));
        onTuoPotilas({ potilas, luokitus, paikka: valittuPaikka });
    }

    return (
        <div style={styles.modalOverlay}>
            <div style={styles.modalContainer}>

                <h2 style={styles.modalHeader}>Tuo potilas</h2>

                <select
                    style={styles.formField}
                    value={valittuId}
                    onChange={e => setValittuId(e.target.value)}
                >
                    <option value="">Valitse potilas...</option>

                    {kaikkiPotilaat.map(p => (
                        <option key={`potilas-${p.id}`} value={p.id}>
                            {p.etunimi} {p.sukunimi} ({p.ika}) ({haePotilaanSijainti(p)})
                        </option>
                    ))}

                </select>

                <div style={{ display: "flex", gap: "10px", justifyContent: "center" }}>
                    <button
                        style={{
                            ...styles.triageBtn,
                            background: luokitus === "A" ? "red" : "#eee",
                            color: luokitus === "A" ? "white" : "black"
                        }}
                        onClick={() => setLuokitus("A")}
                    >
                        A
                    </button>

                    <button
                        style={{
                            ...styles.triageBtn,
                            backgroundColor: luokitus === "B" ? "yellow" : "#eee",
                            color: luokitus === "B" ? "white" : "black"
                        }}
                        onClick={() => setLuokitus("B")}
                    >
                        B
                    </button>

                    <button
                        style={{
                            ...styles.triageBtn,
                            background: luokitus === "C" ? "green" : "#eee",
                            color: luokitus === "C" ? "white" : "black"
                        }}
                        onClick={() => setLuokitus("C")}
                    >
                        C
                    </button>
                </div>

                <select
                    style={styles.formField}
                    value={valittuPaikka}
                    onChange={e => setValittuPaikka(Number(e.target.value))}
                >
                    {Object.keys(paikat).map(numero => (
                        <option key={`paikka-${numero}`} value={numero}>
                            Paikka {numero}
                        </option>
                    ))}
                </select>

                <div style={styles.buttonRow}>
                    <button style={styles.cancelButton} onClick={onClose}>Peruuta</button>
                    <button style={styles.saveButton} onClick={handleSave}>Tallenna</button>
                </div>

            </div>
        </div>
    );
}

const styles = {
    modalOverlay: {
        position: "fixed",
        top: 0, left: 0,
        width: "100vw",
        height: "100vh",
        background: "rgba(0,0,0,0.4)",
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
        zIndex: 9999
    },

    modalContainer: {
        background: "white",
        borderRadius: "12px",
        width: "320px",
        padding: "25px",
        boxShadow: "0px 4px 12px rgba(0,0,0,0.3)",
        display: "flex",
        flexDirection: "column",
        gap: "12px",
    },

    modalHeader: {
        margin: 0,
        textAlign: "center",
    },

    formField: {
        padding: "8px",
        borderRadius: "6px",
        border: "1px solid #ccc",
        width: "100%",
        fontSize: "15px"
    },

    triageBtn: {
        padding: "10px 15px",
        border: "none",
        borderRadius: "6px",
        cursor: "pointer",
        fontWeight: "bold",
        fontSize: "16px"
    },

    buttonRow: {
        display: "flex",
        justifyContent: "space-between",
        marginTop: "10px",
    },

    cancelButton: {
        padding: "8px 12px",
        background: "#ccc",
        border: "none",
        borderRadius: "6px",
        cursor: "pointer"
    },

    saveButton: {
        padding: "8px 12px",
        background: "#007bff",
        color: "white",
        border: "none",
        borderRadius: "6px",
        cursor: "pointer"
    }
};
