import Box from "@mui/material/Box";
import Stack from "@mui/material/Stack";
import Typography from "@mui/material/Typography";
import { PieChart } from "@mui/x-charts/PieChart";

const platforms = [
    { value: 15, label: "Päivystysosasto" },
    { value: 6, label: "Ambulanssi" },
    { value: 9, label: "Odotusaula" },
];

const pieParams = {
    height: 100,
    margin: { right: 5 },
    hideLegend: true,
};

export default function KuormitusData() {
    return (
        <Stack direction="row" width="100%" textAlign="center" spacing={2}>
            <Box flexGrow={1}>
                <Typography>Kuormitus</Typography>

                <PieChart
                    colors={["#ffff00", "#ff0000", "#00ff00"]}   // TÄSSÄ VÄRIT
                    series={[
                        {
                            data: platforms,
                        },
                    ]}
                    {...pieParams}
                />
            </Box>
        </Stack>
    );
}