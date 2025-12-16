import React, { useState } from "react";
const AMBULANSSI_ID = 1;
const ODOTUSAULA_ID = 1;

export default function LisaaPotilas({ onClose, onSuccess }) {
    const [etunimi, setEtunimi] = useState("");
    const [sukunimi, setSukunimi] = useState("");
    const [ika, setIka] = useState("");

    const [kohde, setKohde] = useState("ambulanssi");
    const [loading, setLoading] = useState(false);

    async function handleSubmit() {
        if (!etunimi.trim() || !sukunimi.trim() || !ika) {
            alert("Täytä kaikki pakolliset kentät");
            return;
        }

        setLoading(true);

        try {
            const res = await fetch("http://localhost:8080/api/potilas/lisaa", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    nimi: etunimi,
                    sukunimi,
                    ika: Number(ika)
                })
            });

            if (!res.ok) {
                throw new Error("Potilaan lisäys epäonnistui");
            }

            const potilas = await res.json();
            const potilasId = potilas.id ?? potilas.Id;


            if (kohde === "ambulanssi") {
                await fetch(
                    `http://localhost:8080/api/ambulanssi/lisaa/${potilasId}/${AMBULANSSI_ID}`,
                    { method: "POST" }
                );
            }

            if (kohde === "odotusaula") {
                await fetch(
                    `http://localhost:8080/api/odotusaula/lisaa_aulaan/${potilasId}/${ODOTUSAULA_ID}`,
                    { method: "POST" }
                );
            }

            onSuccess?.();
            onClose?.();

        } catch (err) {
            console.error(err);
            alert(err.message || "Virhe potilasta lisättäessä");
        } finally {
            setLoading(false);
        }
    }

    return (

        <div style={styles.container}>
            <h3 style={styles.title}>Lisää potilas</h3>

            <div style={styles.section}>
                <input
                    placeholder="Etunimi"
                    value={etunimi}
                    onChange={e => setEtunimi(e.target.value)}
                    style={styles.input}
                />
                <input
                    placeholder="Sukunimi"
                    value={sukunimi}
                    onChange={e => setSukunimi(e.target.value)}
                    style={styles.input}
                />
                <input
                    type="number"
                    placeholder="Ikä"
                    value={ika}
                    onChange={e => setIka(e.target.value)}
                    style={styles.input}
                />
            </div>

            <div style={styles.section}>
                <div style={styles.sectionTitle}>Sijoitus</div>

                <label style={styles.label}>
                    <input
                        type="radio"
                        checked={kohde === "ambulanssi"}
                        onChange={() => setKohde("ambulanssi")}
                    />
                    Ambulanssipotilas
                </label>

                <label style={styles.label}>
                    <input
                        type="radio"
                        checked={kohde === "odotusaula"}
                        onChange={() => setKohde("odotusaula")}
                    />
                    Odotusaula
                </label>
            </div>

            <div style={styles.actions}>
                <button
                    onClick={handleSubmit}
                    disabled={loading}
                    style={styles.addBtn}
                >
                    {loading ? "Tallennetaan..." : "Lisää potilas"}
                </button>

                <button onClick={onClose} style={styles.cancelBtn}>
                    Peruuta
                </button>
            </div>
        </div>
    );
}

const styles = {
    container: {
        background: "linear-gradient(135deg, lightslategrey, white, lightslategrey)",
        padding: "12px",
        borderRadius: "20px",
        overflow: "hidden",
        color: "black"

    },
    title: {
        marginBottom: "8px"
    },
    section: {
        marginBottom: "10px",
        display: "flex",
        flexDirection: "column",
        gap: "6px"
    },
    sectionTitle: {
        fontWeight: "600"
    },
    label: {
        fontSize: "14px"
    },
    input: {
        padding: "8px",
        borderRadius: "6px",
        border: "1px solid #ccc"
    },
    actions: {
        display: "flex",
        gap: "8px",
        marginTop: "8px"
    },
    addBtn: {
        flex: 1,
        padding: "8px",
        background: "#28a745",
        color: "white",
        border: "none",
        borderRadius: "6px",
        cursor: "pointer"
    },
    cancelBtn: {
        flex: 1,
        padding: "8px",
        background: "#dc3545",
        color: "white",
        border: "none",
        borderRadius: "6px",
        cursor: "pointer"
    },
};
