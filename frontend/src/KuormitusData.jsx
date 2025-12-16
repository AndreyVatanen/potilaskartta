import { useEffect, useState } from "react";
import Box from "@mui/material/Box";
import Stack from "@mui/material/Stack";
import Typography from "@mui/material/Typography";
import { PieChart } from "@mui/x-charts/PieChart";

export default function KuormitusData() {
    const [data, setData] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const [
                    ambulanssiRes,
                    odotusaulaRes,
                    osastoRes,
                ] = await Promise.all([
                    fetch("http://localhost:8080/api/paivystys/ambulanssipotilaat_kpl/1"),
                    fetch("http://localhost:8080/api/paivystys/odotusaulapotilaat_kpl/1"),
                    fetch("http://localhost:8080/api/paivystys/osastopotilaat_kpl/1"),
                ]);

                const ambulanssi = await ambulanssiRes.json();
                const odotusaula = await odotusaulaRes.json();
                const osasto = await osastoRes.json();

                setData([
                    { value: osasto, label: "Päivystysosasto" },
                    { value: ambulanssi, label: "Ambulanssi" },
                    { value: odotusaula, label: "Odotusaula" },
                ]);
            } catch (err) {
                console.error("Virhe kuormitusdatan haussa:", err);
            }
        };

        fetchData();
    }, []);

    return (
        <Stack direction="row" width="100%" textAlign="center" spacing={2}>
            <Box flexGrow={1}>
                <Typography>Kuormitus</Typography>

                {data.length > 0 && (
                    <PieChart
                        height={100}
                        margin={{ right: 5 }}
                        hideLegend
                        colors={["#ff0000", "#ffff00", "#00ff00"]}
                        series={[
                            {
                                data,
                            },
                        ]}
                    />
                )}
            </Box>
        </Stack>
    );
}
