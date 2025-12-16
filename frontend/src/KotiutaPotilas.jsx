import { useState } from "react";

export default function KotiutaPotilas({
                                           odottava,
                                           ambulanssi,
                                           paikat,
                                           onKotiuta,
                                           onClose
                                       }) {


    const potilaatPaikoissa = Object.values(paikat).filter(Boolean);
    const kaikkiPotilaat = [...odottava, ...ambulanssi, ...potilaatPaikoissa];

    const [valittuId, setValittuId] = useState("");
    const [kotiutusTeksti, setKotiutusTeksti] = useState("");


    function haePotilaanSijainti(potilas) {
        if (odottava.some(p => p.id === potilas.id)) return "odotusaula";
        if (ambulanssi.some(p => p.id === potilas.id)) return "ambulanssi";

        const paikkaNumero = Object.entries(paikat)
            .find(([_, p]) => p?.id === potilas.id)?.[0];

        if (paikkaNumero) return `osasto, paikka ${paikkaNumero}`;

        return "";
    }

    function handleSave() {
        const potilas = kaikkiPotilaat.find(p => p.id === Number(valittuId));
        if (!potilas) return;

        onKotiuta({ potilas, teksti: kotiutusTeksti });
    }

    return (
        <div style={styles.modalOverlay}>
            <div style={styles.modalContainer}>

                <h2 style={styles.modalHeader}>Kotiuta potilas</h2>

                <select
                    style={styles.formField}
                    value={valittuId}
                    onChange={e => setValittuId(e.target.value)}
                >
                    <option value="">Valitse potilas...</option>

                    {kaikkiPotilaat.map(p => (
                        <option key={p.id} value={p.id}>
                            {p.etunimi} {p.sukunimi} ({p.ika}) ({haePotilaanSijainti(p)})
                        </option>
                    ))}

                </select>

                <textarea
                    placeholder="Kirjoita potilaan voinnin yhteenveto..."
                    value={kotiutusTeksti}
                    onChange={e => setKotiutusTeksti(e.target.value)}
                    style={styles.textArea}
                />

                <div style={styles.buttonRow}>
                    <button style={styles.cancelButton} onClick={onClose}>Peruuta</button>
                    <button style={styles.saveButton} onClick={handleSave}>Kotiuta</button>
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
        background: "linear-gradient(135deg, lightslategrey, white, lightslategrey)",
        borderRadius: "12px",
        width: "340px",
        padding: "25px",
        boxShadow: "0px 4px 12px rgba(0,0,0,0.3)",
        display: "flex",
        flexDirection: "column",
        gap: "12px",
    },

    modalHeader: {
        margin: 0,
        textAlign: "center",
        color: "black"
    },

    formField: {
        padding: "8px",
        borderRadius: "6px",
        border: "1px solid #ccc",
        width: "100%",
        fontSize: "15px"
    },

    textArea: {
        padding: "8px",
        borderRadius: "6px",
        border: "1px solid #ccc",
        width: "100%",
        height: "90px",
        fontSize: "15px",
        resize: "none"
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
        background: "#28a745",
        color: "white",
        border: "none",
        borderRadius: "6px",
        cursor: "pointer"
    }
};
