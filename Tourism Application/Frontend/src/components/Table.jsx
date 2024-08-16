import React from "react";
import { Button } from "react-bootstrap";
import { useTable } from "react-table";

function Table({columns, data,handleDelete, handleUpdate}){
    const {
        getTableProps, // table props from react-table
        getTableBodyProps, // table body props from react-table
        headerGroups, // headerGroups, if your table has groupings
        rows, // rows for the table based on the data passed
        prepareRow // Prepare the row (this function needs to be called for each row before getting the row props)
      } = useTable({
        columns,
        data
      });

      console.log({test:rows,headerGroups,prepareRow})

      
    
      /* 
        Render the UI for your table
        - react-table doesn't have UI, it's headless. We just need to put the react-table props from the Hooks, and it will do its magic automatically
      */
      return (
        <table {...getTableProps()}>
          <thead>
            {headerGroups.map(headerGroup => (
              <tr {...headerGroup.getHeaderGroupProps()}>
                {headerGroup.headers.map(column => (
                  <th {...column.getHeaderProps()}>{column.render("Header")}</th>
                ))}
              </tr>
            ))}
          </thead>
          <tbody {...getTableBodyProps()}>
            {rows.map((row, i) => {
              prepareRow(row);
              return (
                <tr {...row.getRowProps()}>
                  {row.cells.map(cell => {
                    console.log("row",{row})
                    return cell.value?<td {...cell.getCellProps()}>{cell.render("Cell")}</td>:(<td><Button onClick={(e)=>handleDelete(row.original.id)}>Delete</Button>
                    <Button onClick={()=>handleUpdate(row.original.id)}>Update</Button>
                    </td>);
                  })}
                  
                </tr>
              );
            })}
          </tbody>
        </table>
      );
    }


export default Table;